package org.mosesidowu.onlinepollingsystem.dtos.responses;

import lombok.Data;
import org.mosesidowu.onlinepollingsystem.data.models.Role;

@Data
public class UserResponseDTO {

    private Long id;
    private String token;
    private String email;
    private String name;
    private String message;
    private Role role;

}
