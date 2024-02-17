package com.msig.school.backend.mapper;

import com.msig.school.backend.entity.User;
import com.msig.school.backend.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    User toEntity(UserDto userDto);
    UserDto toModel(User user);
    List<User> toEntities(List<UserDto> userDtos);
    List<UserDto> toModels(List<User> users);
}
