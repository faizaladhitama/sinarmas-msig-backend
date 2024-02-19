package com.msig.school.backend.mapper;

import com.msig.school.backend.entity.ClassRoom;
import com.msig.school.backend.entity.User;
import com.msig.school.backend.model.ClassRoomDto;
import com.msig.school.backend.model.RegisterDto;
import com.msig.school.backend.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {TeacherMapper.class})
public interface ClassRoomMapper extends BaseMapper<ClassRoomDto, ClassRoom> {
}
