package com.msig.school.backend.controller;

import com.msig.school.backend.entity.Teacher;
import com.msig.school.backend.model.TeacherDto;
import com.msig.school.backend.service.TeacherService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/teacher")
@Slf4j
@SecurityRequirement(name = "Bearer Authentication")
public class TeacherController extends BaseController<TeacherDto, Teacher, Long> {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        super(teacherService);
        this.teacherService = teacherService;
    }
}
