package com.msig.school.backend.mapper;

import com.msig.school.backend.entity.User;
import com.msig.school.backend.model.RegisterDto;
import com.msig.school.backend.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User toEntity(UserDto userDto);
    UserDto toModel(User user);
    List<User> toEntities(List<UserDto> userDtos);
    List<UserDto> toModels(List<User> users);

    @Mapping(source = "email", target = "email")
    @Mapping(source = "username", target = "username")
    User registerToUserEntity(RegisterDto register);
}
