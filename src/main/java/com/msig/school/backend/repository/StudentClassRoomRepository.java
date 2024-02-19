package com.msig.school.backend.repository;

import com.msig.school.backend.entity.Student;
import com.msig.school.backend.entity.StudentClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentClassRoomRepository extends JpaRepository<StudentClassRoom, Long> {
}
