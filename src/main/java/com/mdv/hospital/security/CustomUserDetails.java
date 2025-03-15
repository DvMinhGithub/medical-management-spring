package com.mdv.hospital.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mdv.hospital.entity.Account;

public class CustomUserDetails implements UserDetails {
    private final transient Account user;

    public CustomUserDetails(Account user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = user.getType().name();
        return Collections.singleton((GrantedAuthority) () -> roles);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    public Long getId() {
        return user.getId();
    }
}
