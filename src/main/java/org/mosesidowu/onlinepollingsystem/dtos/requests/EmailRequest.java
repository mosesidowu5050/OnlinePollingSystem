package org.mosesidowu.onlinepollingsystem.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailRequest {
    private String recipient;
    private String subject;
    private String messageBody;
    private String attachment;
}