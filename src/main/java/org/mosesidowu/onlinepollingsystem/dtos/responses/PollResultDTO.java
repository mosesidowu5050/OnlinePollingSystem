package org.mosesidowu.onlinepollingsystem.dtos.responses;

import lombok.Data;

import java.util.List;

@Data
public class PollResultDTO {

    private Long pollId;
    private String pollTitle;
    private String pollDescription;
    private List<OptionResultDTO> results;
    private long totalVotes;

}
