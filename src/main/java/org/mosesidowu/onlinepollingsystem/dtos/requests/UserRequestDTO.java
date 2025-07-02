package org.mosesidowu.onlinepollingsystem.dtos.requests;

import lombok.Data;

import java.util.List;

@Data
public class UserRequestDTO {

    private Long id;
    private String email;
    private String name;
    private List<String> roles;

}
