package com.example.mailsender.mail;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Map;

@Slf4j
@AllArgsConstructor
public abstract class MailComponent {

    private JavaMailSender javaMailSender;

    protected void sendSimpleEmail(MailMessage mail) {
        log.info("Sending email.");

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(mail.getFrom());
            message.setTo(mail.getTo());
            message.setSubject(mail.getSubject());
            message.setText(mail.getMessage());

            this.javaMailSender.send(message);

            log.info("Simple email sent successfully.");
        } catch (Exception e) {
            log.error("Error when tried to send the email.");
        }
    }

    protected void sendAdvancedEmail(MailMessage mail) {
        log.info("Sending email.");

        try {
            MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(mail.getFrom(), "Experts Club - Fuskinha");
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getMessage(), true);

            for (Map.Entry<String, ClassPathResource> map : mail.getAttachments().entrySet()) {
                helper.addAttachment(map.getKey(), map.getValue());
            }

            for (Map.Entry<String, ClassPathResource> map : mail.getBodyFiles().entrySet()) {
                helper.addInline(map.getKey(), map.getValue());
            }

            this.javaMailSender.send(mimeMessage);
            log.info("Advanced email sent successfully.");
        } catch (Exception e) {
            log.error("Error when tried to send the email.");
        }
    }

}
