package com.msig.school.backend.repository;

import com.msig.school.backend.entity.ClassRoom;
import com.msig.school.backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
