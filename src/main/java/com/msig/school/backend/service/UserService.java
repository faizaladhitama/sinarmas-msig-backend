package com.msig.school.backend.service;

import com.msig.school.backend.entity.User;
import com.msig.school.backend.model.LoginDto;
import com.msig.school.backend.model.RegisterDto;
import com.msig.school.backend.model.TokenDto;
import com.msig.school.backend.model.UserDto;

public interface UserService extends BaseService<UserDto, User, Long> {

    UserDto register(RegisterDto register);

    TokenDto login(LoginDto login);
}
