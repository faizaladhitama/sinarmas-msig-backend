package com.msig.school.backend.mapper;

import com.msig.school.backend.entity.StudentClassRoom;
import com.msig.school.backend.model.StudentClassRoomDto;
import com.msig.school.backend.model.TeacherDto;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {StudentMapper.class, ClassRoomMapper.class})
public interface StudentClassRoomMapper extends BaseMapper<StudentClassRoomDto, StudentClassRoom> {
}
