package org.mosesidowu.onlinepollingsystem.security;

import jakarta.servlet.ServletException;
import org.mosesidowu.onlinepollingsystem.config.CustomOAuth2User;
import org.mosesidowu.onlinepollingsystem.data.models.Role;
import org.mosesidowu.onlinepollingsystem.data.repository.UserRepository;
import org.mosesidowu.onlinepollingsystem.services.IUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;
    private final IUserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(UserRepository userRepository, IUserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        // Order matters: More specific permitAll() rules first
                        .requestMatchers("/oauth2/**", "/login/**", "/error").permitAll() // OAuth2 and error pages
                        .requestMatchers("/auth/**").permitAll() // Explicitly permit /auth/status and any other /auth paths
                        // Role-based access for API endpoints
                        .requestMatchers("/api/polls/**").hasRole(Role.ADMIN.name().substring(5))
                        .requestMatchers("/api/votes/**").hasRole(Role.VOTER.name().substring(5))
                        // All other requests require authentication
                        .anyRequest().authenticated() // This should come last
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(oauth2AuthenticationSuccessHandler())
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oauth2UserService())
                        )
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler(logoutSuccessHandler())
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Allow requests from your React frontend's origin(s)
        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
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
    public AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();

                Authentication jwtAuth = new UsernamePasswordAuthenticationToken(
                        customOAuth2User.getUserId(),
                        null,
                        customOAuth2User.getAuthorities()
                );
                String token = jwtTokenProvider.generateToken(jwtAuth);

                String redirectUrl = "http://localhost:3000/oauth2/redirect?token=" + URLEncoder.encode(token, StandardCharsets.UTF_8.toString());
                response.sendRedirect(redirectUrl);
            }
        };
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        SimpleUrlLogoutSuccessHandler handler = new SimpleUrlLogoutSuccessHandler();
        handler.setDefaultTargetUrl("http://localhost:3000/login");
        return handler;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtTokenProvider, userRepository);
    }
}
