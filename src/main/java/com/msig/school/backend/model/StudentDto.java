package com.msig.school.backend.model;

import com.msig.school.backend.entity.StudentClassRoom;
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
public class StudentDto extends BaseDto implements Serializable {
    private String name;
    private UserDto user;
    private Set<StudentClassRoomDto> studentClassRooms;
}
