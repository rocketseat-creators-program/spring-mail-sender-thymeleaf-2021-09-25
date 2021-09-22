package com.example.mailsender.mail;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import org.springframework.core.io.ClassPathResource;

import java.util.Map;

@Getter
@Setter
@Builder
public class MailMessage {

    private String to;
    private String from;
    private String subject;
    private String message;

    @Singular
    private Map<String, ClassPathResource> attachments;

    @Singular
    private Map<String, ClassPathResource> bodyFiles;

}
