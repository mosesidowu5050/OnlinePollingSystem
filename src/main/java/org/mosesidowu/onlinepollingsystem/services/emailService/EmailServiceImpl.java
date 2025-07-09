package org.mosesidowu.onlinepollingsystem.services.emailService;


import lombok.RequiredArgsConstructor;
import org.mosesidowu.onlinepollingsystem.dtos.requests.EmailRequest;
import org.mosesidowu.onlinepollingsystem.exception.MailException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Override
    public void sendEmail(String emailRequest) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(senderEmail);
            message.setTo(emailRequest);
            message.setSubject("Registration Confirmation");
            message.setText("""
                Hello,
            
                Thank you for registering.
                Your email has been successfully registered for the upcoming election.
            
                You'll receive further updates as we get closer to the event.
            
                If you have any questions, feel free to reach out to our support team.
            
                Best regards,
                The Election Committee
                """);

            javaMailSender.send(message);
        } catch (MailException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}