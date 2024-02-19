package com.msig.school.backend.controller;

import com.msig.school.backend.entity.ClassRoom;
import com.msig.school.backend.entity.User;
import com.msig.school.backend.model.*;
import com.msig.school.backend.service.ClassRoomService;
import com.msig.school.backend.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/class-room")
@Slf4j
@SecurityRequirement(name = "Bearer Authentication")
public class ClassRoomController extends BaseController<ClassRoomDto, ClassRoom, Long> {
    private final ClassRoomService classRoomService;

    public ClassRoomController(ClassRoomService classRoomService) {
        super(classRoomService);
        this.classRoomService = classRoomService;
    }
}
