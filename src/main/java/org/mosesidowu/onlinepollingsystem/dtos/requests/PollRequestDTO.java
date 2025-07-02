package org.mosesidowu.onlinepollingsystem.dtos.requests;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PollRequestDTO {

    private String title;
    private String description;
    private List<String> options;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean active;

}
