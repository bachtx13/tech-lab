package com.sem4project.sem4.security.oauth2.user;

import java.util.Map;

public class GithubOAuth2UserInfo extends OAuth2UserInfo {
    public GithubOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getName() {
        return attributes.get("name") == null ? attributes.get("login").toString() : attributes.get("name").toString();
    }

    @Override
    public String getEmail() {
        return attributes.get("email") == null ? attributes.get("html_url").toString() : attributes.get("email").toString();
    }

    @Override
    public String getImageUrl() {
        return attributes.get("avatar_url").toString();
    }
}
