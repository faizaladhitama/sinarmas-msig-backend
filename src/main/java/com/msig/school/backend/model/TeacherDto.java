package com.msig.school.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.msig.school.backend.entity.ClassRoom;
import com.msig.school.backend.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TeacherDto extends BaseDto implements Serializable {
    private String name;
    private UserDto user;
    private Set<ClassRoomDto> classRooms;
}
