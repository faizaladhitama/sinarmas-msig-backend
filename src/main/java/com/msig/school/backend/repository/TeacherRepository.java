package com.msig.school.backend.repository;

import com.msig.school.backend.entity.Teacher;
import com.msig.school.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
