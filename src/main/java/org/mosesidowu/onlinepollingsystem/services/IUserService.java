package org.mosesidowu.onlinepollingsystem.services;

import org.mosesidowu.onlinepollingsystem.data.models.User;
import org.mosesidowu.onlinepollingsystem.dtos.responses.UserResponseDTO;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface IUserService {

    UserResponseDTO registerOAuthUser(OAuth2User user);

    UserResponseDTO  findByEmail(String email);

    UserResponseDTO findById(Long id);

}
