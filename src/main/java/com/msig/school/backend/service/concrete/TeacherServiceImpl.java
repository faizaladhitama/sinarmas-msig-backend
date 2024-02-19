package com.msig.school.backend.service.concrete;

import com.msig.school.backend.entity.Teacher;
import com.msig.school.backend.entity.User;
import com.msig.school.backend.mapper.TeacherMapper;
import com.msig.school.backend.model.TeacherDto;
import com.msig.school.backend.model.UserDto;
import com.msig.school.backend.repository.TeacherRepository;
import com.msig.school.backend.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TeacherServiceImpl extends BaseServiceImpl<TeacherDto, Teacher, Long> implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        super(teacherRepository, teacherMapper);
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public Teacher getObjForExample(TeacherDto teacher){
        return Teacher.builder().name(teacher.getName()).build();
    }
}
