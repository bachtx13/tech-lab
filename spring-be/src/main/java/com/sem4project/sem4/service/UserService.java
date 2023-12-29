package com.sem4project.sem4.service;

import com.sem4project.sem4.common.RoleEnum;
import com.sem4project.sem4.dto.dtomodel.UserDto;
import com.sem4project.sem4.dto.dtomodel.UserInfoDto;
import com.sem4project.sem4.dto.request.LoginRequest;
import com.sem4project.sem4.dto.request.RegisterRequest;
import com.sem4project.sem4.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService extends BaseService<User, UserDto>{
    void login(LoginRequest loginRequest);
    void register(RegisterRequest request);
    void logout();
    UserDto getUserInfo();
    UserInfoDto updateUserInfo(UUID id, UserInfoDto userInfoDto);
    Long countByRole(RoleEnum role);
    void init();
}
