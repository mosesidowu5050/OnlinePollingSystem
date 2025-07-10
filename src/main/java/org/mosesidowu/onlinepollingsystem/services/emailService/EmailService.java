package org.mosesidowu.onlinepollingsystem.services.emailService;


import org.mosesidowu.onlinepollingsystem.dtos.requests.EmailRequest;

public interface EmailService {
    void sendEmail(String emailRequest);

}