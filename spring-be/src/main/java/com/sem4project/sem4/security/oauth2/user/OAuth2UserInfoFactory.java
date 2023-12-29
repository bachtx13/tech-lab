package com.sem4project.sem4.security.oauth2.user;

import com.sem4project.sem4.common.AuthEnum;
import com.sem4project.sem4.exception.OAuth2AuthenticationProcessingException;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(AuthEnum.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthEnum.facebook.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        }  else if (registrationId.equalsIgnoreCase(AuthEnum.github.toString())) {
            return new GithubOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Login with " + registrationId + " is not supported yet!");
        }
    }
}
