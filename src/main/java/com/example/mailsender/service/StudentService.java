package com.example.mailsender.service;

import com.example.mailsender.mail.StudentMailComponent;
import com.example.mailsender.model.Student;
import com.example.mailsender.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;
    private StudentMailComponent studentMailComponent;

    public Student save(Student student) {
        this.studentRepository.save(student);
        //this.studentMailComponent.sendWelcomeEmail(student);
        this.studentMailComponent.sendSimpleWelcomeEmail(student);
        return student;
    }

}
