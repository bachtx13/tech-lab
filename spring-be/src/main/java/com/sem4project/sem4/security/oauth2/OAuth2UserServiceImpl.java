package com.sem4project.sem4.security.oauth2;

import com.sem4project.sem4.common.RoleEnum;
import com.sem4project.sem4.entity.Role;
import com.sem4project.sem4.entity.User;
import com.sem4project.sem4.entity.UserInfo;
import com.sem4project.sem4.entity.UserPrincipal;
import com.sem4project.sem4.exception.OAuth2AuthenticationProcessingException;
import com.sem4project.sem4.repository.RoleRepository;
import com.sem4project.sem4.repository.UserInfoRepository;
import com.sem4project.sem4.repository.UserRepository;
import com.sem4project.sem4.security.oauth2.user.OAuth2UserInfo;
import com.sem4project.sem4.security.oauth2.user.OAuth2UserInfoFactory;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@ComponentScan(basePackages = { "com.sem4project.sem4.entity" })
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try{
            return processOAuthUser(oAuth2UserRequest, oAuth2User);
        } catch (Exception ex){
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }
    private OAuth2User processOAuthUser(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User){
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if (oAuth2UserInfo.getEmail().isEmpty()){
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }
        User user = userRepository.findByOauth2Email(oAuth2UserInfo.getEmail()).orElse(null);
        if(user != null){
            if(!user.getProvider().equals(oAuth2UserRequest.getClientRegistration().getRegistrationId())){
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            }
            updateExistingUser(user, oAuth2UserInfo);
        }
        else {
            user = registerOAuth2(oAuth2UserRequest, oAuth2UserInfo);
        }
        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }
    private User registerOAuth2(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo){
        User user = new User();
        user.setProvider(oAuth2UserRequest.getClientRegistration().getRegistrationId());
        user.setProviderId(oAuth2UserInfo.getId());
        UserInfo userInfo = new UserInfo();
        userInfo.setFullName(oAuth2UserInfo.getName());
        user.setOauth2Email(oAuth2UserInfo.getEmail());
        user.setUserInfo(userInfo);
        List<Role> defaultRoles = new ArrayList<>();
        Role defaultRole = roleRepository.findByName(RoleEnum.ROLE_USER.name()).orElseThrow(IllegalArgumentException::new);
        defaultRoles.add(defaultRole);
        user.setRoles(defaultRoles);
        return userRepository.save(user);
    }
    private void updateExistingUser(User user, OAuth2UserInfo oAuth2UserInfo) {
        UserInfo userInfo = user.getUserInfo();
        userInfo.setFullName(oAuth2UserInfo.getName());
        userInfo.setAvatar(oAuth2UserInfo.getImageUrl());
//        userInfoRepository.save(userInfo);
        userRepository.save(user);
    }
}
