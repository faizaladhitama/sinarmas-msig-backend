package com.msig.school.backend.service.concrete;

import com.msig.school.backend.entity.ClassRoom;
import com.msig.school.backend.entity.Student;
import com.msig.school.backend.mapper.ClassRoomMapper;
import com.msig.school.backend.model.*;
import com.msig.school.backend.repository.ClassRoomRepository;
import com.msig.school.backend.service.ClassRoomService;
import com.msig.school.backend.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class ClassRoomServiceImpl extends BaseServiceImpl<ClassRoomDto, ClassRoom, Long> implements ClassRoomService {
    private final ClassRoomRepository classRoomRepository;
    private final ClassRoomMapper classRoomMapper;

    public ClassRoomServiceImpl(ClassRoomRepository classRoomRepository, ClassRoomMapper classRoomMapper) {
        super(classRoomRepository, classRoomMapper);
        this.classRoomRepository = classRoomRepository;
        this.classRoomMapper = classRoomMapper;
    }

    @Override
    public ClassRoom getObjForExample(ClassRoomDto classRoom){
        return ClassRoom.builder().name(classRoom.getName()).build();
    }
}
