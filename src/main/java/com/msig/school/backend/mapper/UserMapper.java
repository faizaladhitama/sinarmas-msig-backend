package com.msig.school.backend.mapper;

import com.msig.school.backend.entity.User;
import com.msig.school.backend.model.RegisterDto;
import com.msig.school.backend.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserDto, User> {
    @Mapping(source = "email", target = "email")
    @Mapping(source = "username", target = "username")
    User registerToUserEntity(RegisterDto register);
}
