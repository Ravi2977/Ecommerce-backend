package com.Ravicomputer.ecommerce.Services;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    public void sendEmailWithEmbeddedImages(String to, String subject, List<String> imageUrls, String messageText) throws MessagingException, javax.mail.MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);

        StringBuilder htmlMsg = new StringBuilder("<html><body>")
                .append("<h3 style='color: #007bff;'>Your Ordered Items</h3>")
                .append("<p style='font-weight: bold; color: #28a745;'>").append(messageText).append("</p>");

        for (String imageUrl : imageUrls) {
            htmlMsg.append("<img src='").append(imageUrl).append("' alt='Ordered Item' style='width:600px;height:auto;'/>");
        }

        htmlMsg.append("</body></html>");

        helper.setText(htmlMsg.toString(), true);

        javaMailSender.send(mimeMessage);
    }

}
