package com.msig.school.backend.service.concrete;

import com.msig.school.backend.entity.User;
import com.msig.school.backend.mapper.UserMapper;
import com.msig.school.backend.model.UserDto;
import com.msig.school.backend.repository.UserRepository;
import com.msig.school.backend.service.UserService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> getList(Pageable pageable) {
        List<User> users = userRepository.findAll(pageable);
        return userMapper.toModels(users);
    }

    @Override
    public UserDto getById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return userMapper.toModel(optionalUser.orElse(null));
    }

    @Override
    @Transactional
    public UserDto updateById(Long id, UserDto userDto) {
        User user = userMapper.toEntity(this.getById(id));
        if(Objects.isNull(user)){
            return null;
        }
        user = userMapper.toEntity(userDto);
        return userMapper.toModel(userRepository.save(user));
    }

    @Override
    @Transactional
    public Boolean deleteById(Long id) {
        User getUser = userMapper.toEntity(this.getById(id));
        if(Objects.isNull(getUser)){
            return Boolean.FALSE;
        }
        userRepository.deleteById(id);
        return  Boolean.TRUE;
    }

    @Override
    @Transactional
    public UserDto create(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toModel(userRepository.save(user));
    }
}
