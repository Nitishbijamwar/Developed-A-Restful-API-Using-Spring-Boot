package com.training.app.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.training.app.Context;
import com.training.app.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication ret = null;
        for (User user : Context.getUsersAsList()) {
            if (user.getName().equals(authentication.getPrincipal().toString())
                    && Boolean.TRUE.equals(CustomPasswordEncoder.matches(authentication.getCredentials().toString(), user.getPassword()))) {
                    ret = new UsernamePasswordAuthenticationToken(new User(UUID.randomUUID(), "admin", CustomPasswordEncoder.encode("admin"),"info@gmail.com"), authentication.getCredentials(), getAuthorities(user.getId()));
                    break;
            }
        }
        return ret;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

    private List<GrantedAuthority> getAuthorities(UUID id) {
        List<GrantedAuthority> ret = new ArrayList<>();
        Context.getUsers().get(id).getRoles().forEach(role -> ret.add(new SimpleGrantedAuthority(role)));
        return ret;
    }
}
