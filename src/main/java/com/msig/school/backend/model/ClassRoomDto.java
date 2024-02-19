package com.msig.school.backend.model;

import com.msig.school.backend.entity.StudentClassRoom;
import com.msig.school.backend.entity.Teacher;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class ClassRoomDto extends BaseDto implements Serializable {
    private String name;
    private Integer year;
    private TeacherDto teacher;
    private Set<StudentClassRoomDto> studentClassRooms;
}
