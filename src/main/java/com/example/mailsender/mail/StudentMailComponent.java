package com.example.mailsender.mail;

import com.example.mailsender.model.Student;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;

@Component
public class StudentMailComponent extends MailComponent {

    private TemplateEngine templateEngine;

    public StudentMailComponent(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
        super(javaMailSender);
        this.templateEngine = templateEngine;
    }

    public void sendSimpleWelcomeEmail(Student student) {
        MailMessage mail = MailMessage.builder()
                .to(student.getEmail())
                .from("fuskinhatestes@gmail.com")
                .message(String.format("Olá, %s! Espero que você tenha curtido a aula.", student.getName()))
                .subject("Aula do Experts Club - Spring Mail Sender")
                .build();

        this.sendSimpleEmail(mail);
    }


    public void sendWelcomeEmail(Student student) {
        Context context = new Context();
        context.setVariable("name", student.getName());
        context.setVariable("email", student.getEmail());
        context.setVariable("birthday", student.getBirthday());
        context.setVariable("date", LocalDate.now());

        String templateMessage = this.templateEngine.process("welcome-template", context);

        MailMessage mail = MailMessage.builder()
                .to(student.getEmail())
                .from("fuskinhatestes@gmail.com")
                .message(templateMessage)
                .subject("Aula do Experts Club - Spring Mail Sender")
                .bodyFile("headerLogo", new ClassPathResource("static/images/Logo_ExpertsClub.png"))
                .attachment("Apresentacao.pptx", new ClassPathResource("static/docs/Apresentacao_Spring_Mail_Thymeleaf.pptx"))
                .build();

        this.sendAdvancedEmail(mail);
    }

}
