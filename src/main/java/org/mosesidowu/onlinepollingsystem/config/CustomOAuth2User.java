package org.mosesidowu.onlinepollingsystem.config;

import lombok.Getter;
import org.mosesidowu.onlinepollingsystem.data.models.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;


public class CustomOAuth2User implements OAuth2User {

    private OAuth2User oauth2User;
    @Getter
    private Long userId;
    @Getter
    private Role role;

    public CustomOAuth2User(OAuth2User oauth2User, Long userId, Role role) {
        this.oauth2User = oauth2User;
        this.userId = userId;
        this.role = role;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getName() {
        return oauth2User.getName();
    }
}
