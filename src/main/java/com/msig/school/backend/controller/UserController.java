package com.msig.school.backend.controller;

import com.msig.school.backend.entity.User;
import com.msig.school.backend.model.*;
import com.msig.school.backend.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
@Slf4j
@SecurityRequirement(name = "Bearer Authentication")
public class UserController extends BaseController<UserDto, User, Long> {
    private final UserService userService;

    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    @RateLimiter(name = "userController")
    @CircuitBreaker(name = "userController")
    public @ResponseBody ResponseDto<UserDto> register(@RequestBody RegisterDto register) {
        return ResponseDto.of(userService.register(register), HttpStatus.OK, "Register success");
    }

    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    @RateLimiter(name = "userController")
    @CircuitBreaker(name = "userController")
    public @ResponseBody ResponseDto<TokenDto> login(@RequestBody LoginDto login) {
        return ResponseDto.of(userService.login(login),HttpStatus.OK, "Login success");
    }
}
