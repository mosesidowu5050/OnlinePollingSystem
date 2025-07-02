package org.mosesidowu.onlinepollingsystem.dtos.responses;

import lombok.Data;

@Data
public class OptionResponseDTO {

    private Long id;
    private String text;
    private int voteCount;

}
