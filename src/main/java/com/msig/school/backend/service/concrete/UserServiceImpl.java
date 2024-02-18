package com.msig.school.backend.service.concrete;

import com.msig.school.backend.entity.User;
import com.msig.school.backend.mapper.UserMapper;
import com.msig.school.backend.model.LoginDto;
import com.msig.school.backend.model.RegisterDto;
import com.msig.school.backend.model.TokenDto;
import com.msig.school.backend.model.UserDto;
import com.msig.school.backend.repository.UserRepository;
import com.msig.school.backend.service.UserService;
import com.msig.school.backend.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    @Transactional
    public UserDto register(RegisterDto register) {
        Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        String hashedPassword = encoder.encode(register.getPassword());
        User user = userMapper.registerToUserEntity(register);
        user.setHashedPassword(hashedPassword);
        return userMapper.toModel(userRepository.save(user));
    }

    @Override
    public TokenDto login(LoginDto login) {
        Optional<User> optionalUser = userRepository.findByEmail(login.getEmail());
        User user = optionalUser.orElse(null);
        if(Objects.isNull(user)){
            return null;
        }
        Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        boolean matches = encoder.matches(login.getPassword(), user.getHashedPassword());
        if(!matches){
            return null;
        }
        String token = JwtUtil.generateToken(userMapper.toModel(user));
        return TokenDto.builder().token(token).expiredAt(JwtUtil.tokenExpiration()).generatedAt(LocalDateTime.now()).build();
    }
}
