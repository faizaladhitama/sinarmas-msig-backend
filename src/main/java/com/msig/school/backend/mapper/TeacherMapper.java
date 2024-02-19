package com.msig.school.backend.mapper;

import com.msig.school.backend.entity.Student;
import com.msig.school.backend.entity.Teacher;
import com.msig.school.backend.model.StudentDto;
import com.msig.school.backend.model.TeacherDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class})
public interface TeacherMapper extends BaseMapper<TeacherDto, Teacher> {
}
