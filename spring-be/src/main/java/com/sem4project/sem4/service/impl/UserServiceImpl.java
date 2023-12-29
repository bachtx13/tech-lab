package com.sem4project.sem4.service.impl;

import com.sem4project.sem4.common.RoleEnum;
import com.sem4project.sem4.dto.dtomodel.UserDto;
import com.sem4project.sem4.dto.dtomodel.UserInfoDto;
import com.sem4project.sem4.dto.request.LoginRequest;
import com.sem4project.sem4.dto.request.RegisterRequest;
import com.sem4project.sem4.entity.Role;
import com.sem4project.sem4.entity.User;
import com.sem4project.sem4.entity.UserPrincipal;
import com.sem4project.sem4.entity.UserInfo;
import com.sem4project.sem4.exception.AuthException;
import com.sem4project.sem4.exception.ResourceNotFoundException;
import com.sem4project.sem4.exception.UpdateResourceException;
import com.sem4project.sem4.mapper.RoleMapper;
import com.sem4project.sem4.mapper.UserInfoMapper;
import com.sem4project.sem4.mapper.UserMapper;
import com.sem4project.sem4.repository.RoleRepository;
import com.sem4project.sem4.repository.UserInfoRepository;
import com.sem4project.sem4.repository.UserRepository;
import com.sem4project.sem4.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
//@Transactional
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final RoleMapper roleMapper = RoleMapper.INSTANCE;
    private final UserInfoMapper userInfoMapper = UserInfoMapper.INSTANCE;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public void login(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {
            throw new AuthException("Username or password incorrect");
        }
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        try {
            if (!userRepository.existsByEmail(registerRequest.getEmail())) {
                String passwordEncoded = passwordEncoder.encode(registerRequest.getPassword());
                User userRegister = userMapper.fromRegisterRequest(registerRequest);
                userRegister.setPassword(passwordEncoded);
                List<Role> defaultRoles = new ArrayList<>();
                Role defaultRole = roleRepository.findByName(RoleEnum.ROLE_USER.name()).orElseThrow(IllegalArgumentException::new);
                defaultRoles.add(defaultRole);
                userRegister.setRoles(defaultRoles);
                userRepository.save(userRegister);
            } else {
                throw new AuthException("Email already exist");
            }
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Default role doesn't exists");
        }
    }

    @Override
    public UserDto getById(UUID id) {
        return null;
    }

    @Override
    public List<UserDto> getAll(Boolean isDisable, Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        try {
            List<User> users;
            if (isDisable == null) {
                users = userRepository.findAll();
            } else{
                users = userRepository.findAllByDisable(isDisable);
            }
            return users.stream().map(user -> {
                UserDto userDto = userMapper.toDto(user);
                userDto.setUserInfo(userInfoMapper.toDto(user.getUserInfo()));
                return userDto;
            }).toList();
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }

    @Override
    public List<UserDto> getAllAvailable(Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
        return null;
    }

    @Override
    public UserDto create(UserDto brandDto) {
        return null;
    }

    @Override
    public UserDto update(UUID id, UserDto brandDto) {
        return null;
    }

    @Override
    public void updateDisable(UUID id) {

    }

    @Override
    public Long count(Boolean isDisable) {
        if(isDisable == null){
            return userRepository.count();
        }
        return userRepository.countByDisable(isDisable);
    }

    @Override
    public void logout() {
        try {
//            SecurityContextHolder.clearContext();
            return;
        } catch (Exception ex) {
            throw new AuthException("Not logged in yet");
        }
    }

    @Override
    public UserDto getUserInfo() {
        try {
            UserPrincipal userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findById(userDetails.getId()).orElseThrow(IllegalArgumentException::new);
            UserDto userDto = userMapper.toDto(user);
            userDto.setRoles(user.getRoles().stream().map(roleMapper::toDto).toList());
            userDto.setUserInfo(userInfoMapper.toDto(user.getUserInfo()));
            return userDto;
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Override
    public UserInfoDto updateUserInfo(UUID id, UserInfoDto userInfoDto) {
        try {
            User user = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            UserInfo userInfo = user.getUserInfo();
            UserInfo updatedUserInfo;
            if (userInfo == null) {
                user.setUserInfo(userInfoMapper.toEntity(userInfoDto));
                User updatedUser = userRepository.save(user);
//                userRepository.refresh(updatedUser);
                updatedUserInfo = updatedUser.getUserInfo();
            } else {
                userInfoMapper.transferToEntity(userInfo, userInfoDto);
                updatedUserInfo = userInfoRepository.save(userInfo);
//                userInfoRepository.refresh(updatedUserInfo);
            }
            return userInfoMapper.toDto(updatedUserInfo);
        } catch (IllegalArgumentException | EntityNotFoundException ex) {
            logger.error(ex.getMessage());
            throw new AuthException("Not logged in yet");
        } catch (OptimisticEntityLockException ex){
            throw new UpdateResourceException("Update user info failed");
        }
    }

    @Override
    public Long countByRole(RoleEnum role) {
        return userRepository.countUsersByRoleName(role.name());
    }

    @Override
    public void init() {
        try {
            String adminEmail = "admin@gmail.com";
            if (!userRepository.existsByEmail(adminEmail)) {
                String passwordEncoded = passwordEncoder.encode("admin");
                User userRegister = new User();
                userRegister.setEmail(adminEmail);
                userRegister.setPassword(passwordEncoded);
                List<Role> defaultRoles = new ArrayList<>();
                Role defaultRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN.name()).orElseThrow(IllegalArgumentException::new);
                defaultRoles.add(defaultRole);
                userRegister.setRoles(defaultRoles);
                userRepository.save(userRegister);
            } else {
                throw new AuthException("Email already exist");
            }
        } catch (IllegalArgumentException ex) {
            throw new ResourceNotFoundException("Default role doesn't exists");
        }
    }
}
