package com.msig.school.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "StudentClassRoomEntity")
@Table(name = "student_class_room")
public class StudentClassRoom extends Base implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "class_room_id", referencedColumnName = "id", nullable = false)
    private ClassRoom classRoom;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    private Student student;
}
