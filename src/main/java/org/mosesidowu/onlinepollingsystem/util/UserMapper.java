package org.mosesidowu.onlinepollingsystem.util;

import org.mosesidowu.onlinepollingsystem.data.models.User;
import org.mosesidowu.onlinepollingsystem.dtos.responses.UserResponseDTO;

public class UserMapper {

    public static UserResponseDTO getRegisteredUserResponse(User savedUser) {
        UserResponseDTO userResponseDTO = getResponseDTO(savedUser);
        userResponseDTO.setMessage("User registered successfully via OAuth");

        return userResponseDTO;
    }

    public static UserResponseDTO getResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setRole(user.getRole());

        return userResponseDTO;
    }

}
