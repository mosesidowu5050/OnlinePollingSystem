package org.mosesidowu.onlinepollingsystem.services.userService;

import org.mosesidowu.onlinepollingsystem.dtos.responses.UserResponseDTO;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface IUserService {

    UserResponseDTO registerOAuthUser(OAuth2User user);

    UserResponseDTO  findByEmail(String email);

    UserResponseDTO findById(Long id);

}
