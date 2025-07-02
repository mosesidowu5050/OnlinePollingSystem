package org.mosesidowu.onlinepollingsystem.dtos.responses;

import lombok.Data;

@Data
public class OptionResultDTO {

    private Long optionId;
    private String optionText;
    private int voteCount;
    private double percentage;

}
