package com.msig.school.backend.controller;

import com.msig.school.backend.entity.Student;
import com.msig.school.backend.model.StudentDto;
import com.msig.school.backend.service.StudentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/student")
@Slf4j
@SecurityRequirement(name = "Bearer Authentication")
public class StudentController extends BaseController<StudentDto, Student, Long> {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        super(studentService);
        this.studentService = studentService;
    }
}
