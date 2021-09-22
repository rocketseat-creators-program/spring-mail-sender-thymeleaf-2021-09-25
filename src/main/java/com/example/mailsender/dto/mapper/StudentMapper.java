package com.example.mailsender.dto.mapper;

import com.example.mailsender.dto.StudentRequestDto;
import com.example.mailsender.model.Student;

public class StudentMapper {

    public static Student fromDto(StudentRequestDto dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPassword(dto.getPassword());
        student.setBirthday(dto.getBirthday());

        return student;
    }

}
