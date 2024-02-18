package com.msig.school.backend.controller;

import com.msig.school.backend.entity.User;
import com.msig.school.backend.model.LoginDto;
import com.msig.school.backend.model.RegisterDto;
import com.msig.school.backend.model.TokenDto;
import com.msig.school.backend.model.UserDto;
import com.msig.school.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
@Slf4j
public class UserController extends BaseController<UserDto, User, Long>{
    private final UserService userService;

    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public @ResponseBody UserDto register(@RequestBody  RegisterDto register){
        return userService.register(register);
    }

    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    public @ResponseBody TokenDto login(@RequestBody LoginDto login){
        return userService.login(login);
    }
}
