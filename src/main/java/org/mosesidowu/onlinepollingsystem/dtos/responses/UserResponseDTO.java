package org.mosesidowu.onlinepollingsystem.dtos.responses;

import lombok.Data;
import org.mosesidowu.onlinepollingsystem.data.models.Role;

@Data
public class UserResponseDTO {

    private Long id;
    private String email;
    private String name;
    private Role role;

}
