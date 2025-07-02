package org.mosesidowu.onlinepollingsystem.dtos.responses;

import lombok.Data;
import org.mosesidowu.onlinepollingsystem.data.models.PollStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PollResponseDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private PollStatus status;
    private UserResponseDTO creator;
    private List<OptionResponseDTO> options;

}
