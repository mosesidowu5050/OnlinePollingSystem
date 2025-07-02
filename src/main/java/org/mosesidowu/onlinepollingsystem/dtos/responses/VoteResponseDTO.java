package org.mosesidowu.onlinepollingsystem.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteResponseDTO {

    private Long id;
    private Long pollId;
    private Long optionId;
    private Long userId;
    private LocalDateTime timestamp;
    private String message;

}
