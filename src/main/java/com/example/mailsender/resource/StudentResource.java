package com.example.mailsender.resource;

import com.example.mailsender.dto.StudentRequestDto;
import com.example.mailsender.dto.mapper.StudentMapper;
import com.example.mailsender.model.Student;
import com.example.mailsender.repository.StudentRepository;
import com.example.mailsender.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentResource {

    private StudentService studentService;
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Student> save(@Valid @RequestBody StudentRequestDto studentRequestDto) {
        Student student = StudentMapper.fromDto(studentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.studentService.save(student));
    }

}
