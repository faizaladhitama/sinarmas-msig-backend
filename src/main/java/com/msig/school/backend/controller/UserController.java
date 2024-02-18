package com.msig.school.backend.controller;

import com.msig.school.backend.entity.User;
import com.msig.school.backend.model.LoginDto;
import com.msig.school.backend.model.RegisterDto;
import com.msig.school.backend.model.TokenDto;
import com.msig.school.backend.model.UserDto;
import com.msig.school.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(path = "/", produces = "application/json")
    public @ResponseBody List<UserDto> getList(Pageable pageable){
        return userService.getList(pageable);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public @ResponseBody UserDto get(@PathVariable("id") Long id){
        return userService.getById(id);
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public @ResponseBody UserDto create(@RequestBody  UserDto userDto){
        return userService.create(userDto);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public @ResponseBody UserDto update(@PathVariable("id") Long id, UserDto userDto){
        return userService.updateById(id, userDto);
    }

    @DeleteMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public @ResponseBody Boolean delete(@PathVariable("id") Long id){
        return userService.deleteById(id);
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
