package com.msig.school.backend.service.concrete;

import com.msig.school.backend.entity.ClassRoom;
import com.msig.school.backend.entity.StudentClassRoom;
import com.msig.school.backend.mapper.StudentClassRoomMapper;
import com.msig.school.backend.model.ClassRoomDto;
import com.msig.school.backend.model.StudentClassRoomDto;
import com.msig.school.backend.repository.StudentClassRoomRepository;
import com.msig.school.backend.service.StudentClassRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentClassRoomServiceImpl extends BaseServiceImpl<StudentClassRoomDto, StudentClassRoom, Long> implements StudentClassRoomService {
    private final StudentClassRoomRepository studentClassRoomRepository;
    private final StudentClassRoomMapper studentClassRoomMapper;

    public StudentClassRoomServiceImpl(StudentClassRoomRepository studentClassRoomRepository, StudentClassRoomMapper studentClassRoomMapper) {
        super(studentClassRoomRepository, studentClassRoomMapper);
        this.studentClassRoomRepository = studentClassRoomRepository;
        this.studentClassRoomMapper = studentClassRoomMapper;
    }
}
