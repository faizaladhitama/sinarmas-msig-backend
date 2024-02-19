package com.msig.school.backend.controller;

import com.msig.school.backend.entity.StudentClassRoom;
import com.msig.school.backend.model.StudentClassRoomDto;
import com.msig.school.backend.service.StudentClassRoomService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/student-class-room")
@Slf4j
@SecurityRequirement(name = "Bearer Authentication")
public class StudentClassRoomController extends BaseController<StudentClassRoomDto, StudentClassRoom, Long> {
    private final StudentClassRoomService studentClassRoomService;

    public StudentClassRoomController(StudentClassRoomService studentClassRoomService) {
        super(studentClassRoomService);
        this.studentClassRoomService = studentClassRoomService;
    }
}
