package org.mosesidowu.onlinepollingsystem.security;

import lombok.RequiredArgsConstructor;
import org.mosesidowu.onlinepollingsystem.config.CustomOAuth2User;
import org.mosesidowu.onlinepollingsystem.data.models.Role;
import org.mosesidowu.onlinepollingsystem.data.models.User;
import org.mosesidowu.onlinepollingsystem.data.repository.UserRepository;
import org.mosesidowu.onlinepollingsystem.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import java.util.Optional;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final UserRepository userRepository;
    private final IUserService userService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/oauth2/**", "/login/**", "/error").permitAll()
        .requestMatchers("/api/polls/**").hasRole(Role.ADMIN.name().substring(5)) 
        .requestMatchers("/api/votes/**").hasRole(Role.VOTER.name().substring(5)) 
        .anyRequest().authenticated()
        )
            .oauth2Login(oauth2 -> oauth2
            .defaultSuccessUrl("http://localhost:3000/oauth2/redirect", true) 
            .userInfoEndpoint(userInfo -> userInfo
            .userService(oauth2UserService())
            )
            )
                    .sessionManagement(session -> session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )
                    .logout(logout -> logout
                            .logoutUrl("/api/auth/logout")
                            .logoutSuccessHandler(logoutSuccessHandler())
                            .permitAll()
                    );

            return http.build();
        }


    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService() {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        return userRequest -> {
            OAuth2User oauth2User = delegate.loadUser(userRequest);
            userService.registerOAuthUser(oauth2User);
            org.mosesidowu.onlinepollingsystem.data.models.User internalUser =
                    userRepository.findUsersByOauth2Id(oauth2User.getName())
                            .orElseThrow(() -> new OAuth2AuthenticationException("User not found after registration/update"));

            return new CustomOAuth2User(oauth2User, internalUser.getId(), internalUser.getRole());
        };
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        SimpleUrlLogoutSuccessHandler handler = new SimpleUrlLogoutSuccessHandler();
        handler.setDefaultTargetUrl("http://localhost:3000/login");
        return handler;
    }
}
