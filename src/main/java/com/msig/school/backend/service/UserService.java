package com.msig.school.backend.service;

import com.msig.school.backend.entity.User;
import com.msig.school.backend.model.UserDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserDto> getList(Pageable pageable);

    UserDto getById(Long id);

    UserDto updateById(Long id, UserDto user);

    Boolean deleteById(Long id);

    UserDto create(UserDto user);
}
