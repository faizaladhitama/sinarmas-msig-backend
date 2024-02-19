package com.msig.school.backend.service;

import com.msig.school.backend.entity.User;
import com.msig.school.backend.model.LoginDto;
import com.msig.school.backend.model.RegisterDto;
import com.msig.school.backend.model.TokenDto;
import com.msig.school.backend.model.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService extends BaseService<UserDto, User, Long> {

    UserDto register(RegisterDto register);

    TokenDto login(LoginDto login);
    UserDetails loadUserByUsername(String username);
}
