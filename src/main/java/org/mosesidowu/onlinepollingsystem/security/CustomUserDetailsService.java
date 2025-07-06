package org.mosesidowu.onlinepollingsystem.security;


import jdk.jshell.spi.ExecutionControl;
import org.mosesidowu.onlinepollingsystem.data.models.User;
import org.mosesidowu.onlinepollingsystem.data.repository.UserRepository;
import org.mosesidowu.onlinepollingsystem.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUsersByEmail(email)
                .orElseThrow(() -> new UserException("User not found"));

        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().name())
        );

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getName(),
                authorities
        );
    }
}
