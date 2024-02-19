package com.msig.school.backend.service.concrete;

import com.msig.school.backend.entity.Student;
import com.msig.school.backend.entity.Teacher;
import com.msig.school.backend.mapper.StudentMapper;
import com.msig.school.backend.model.StudentDto;
import com.msig.school.backend.model.TeacherDto;
import com.msig.school.backend.repository.StudentRepository;
import com.msig.school.backend.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentDto, Student, Long> implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        super(studentRepository, studentMapper);
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public Student getObjForExample(StudentDto student){
        return Student.builder().name(student.getName()).build();
    }
}
