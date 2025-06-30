package com.example.ChakriHub.config.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMatchingJobNotificationEmail(String toEmail) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(toEmail);
            helper.setSubject("🎯 A Job Post Matches Your Profile!");

            String text = "Good news!\n\n" +
                    "A job posting has been found that perfectly matches your profile and preferences.\n" +
                    "We recommend you to review and apply at your earliest convenience.\n\n" +
                    "Best of luck!\n\n" +
                    "— ChakriHub Team";

            String html = "<html><body>"
                    + "<h3 style='color:green;'>🎯 Job Match Alert</h3>"
                    + "<p>Good news!</p>"
                    + "<p>A job posting has been found that perfectly matches your profile and preferences.</p>"
                    + "<p><b>We recommend you to review and apply at your earliest convenience.</b></p>"
                    + "<br/>"
                    + "<p>Best of luck!<br/>— <strong>ChakriHub Team</strong></p>"
                    + "</body></html>";

            helper.setText(text, html);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send job match email", e);
        }
    }






}