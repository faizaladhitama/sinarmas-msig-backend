package com.msig.school.backend.model;

import com.msig.school.backend.entity.ClassRoom;
import com.msig.school.backend.entity.Student;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentClassRoomDto extends BaseDto implements Serializable {
    private ClassRoomDto classRoom;
    private StudentDto student;
}
