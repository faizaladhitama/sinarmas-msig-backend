package com.msig.school.backend.service;

import com.msig.school.backend.entity.User;
import com.msig.school.backend.model.LoginDto;
import com.msig.school.backend.model.RegisterDto;
import com.msig.school.backend.model.TokenDto;
import com.msig.school.backend.model.UserDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserDto> getList(Pageable pageable);

    UserDto getById(Long id);

    UserDto updateById(Long id, UserDto user);

    Boolean deleteById(Long id);

    UserDto create(UserDto user);

    UserDto register(RegisterDto register);
    TokenDto login(LoginDto login);
}
