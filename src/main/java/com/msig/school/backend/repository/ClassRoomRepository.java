package com.msig.school.backend.repository;

import com.msig.school.backend.entity.ClassRoom;
import com.msig.school.backend.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
}
