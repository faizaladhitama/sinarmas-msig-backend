package com.msig.school.backend.service.concrete;

import com.msig.school.backend.entity.User;
import com.msig.school.backend.mapper.UserMapper;
import com.msig.school.backend.model.*;
import com.msig.school.backend.repository.UserRepository;
import com.msig.school.backend.service.UserService;
import com.msig.school.backend.util.JwtUtil;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDto, User, Long> implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    @Value("${default.password}")
    private String defaultPassword;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, JwtUtil jwtUtil) {
        super(userRepository, userMapper);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional
    public UserDto create(UserDto userDto) {
        return create(userDto, defaultPassword);
    }

    @Transactional
    public UserDto create(UserDto userDto, String password) {
        Example<User> example = getExample(userDto);
        if(!Objects.isNull(example)){
            User entityFound = uniqueValidation(example);
            if(!Objects.isNull(entityFound)){
                throw new EntityExistsException();
            }
        }

        Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        String hashedPassword = encoder.encode(password);
        userDto.setHashedPassword(hashedPassword);
        User user = userMapper.toEntity(userDto);
        return userMapper.toModel(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserDto register(RegisterDto register) {
        User user = userMapper.registerToUserEntity(register);
        UserDto userDto = userMapper.toModel(user);
        return create(userDto, register.getPassword());
    }

    @Override
    public TokenDto login(LoginDto login) {
        Optional<User> optionalUser = userRepository.findByEmail(login.getEmail());
        User user = optionalUser.orElse(null);
        if (Objects.isNull(user)) {
            return null;
        }
        Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        boolean matches = encoder.matches(login.getPassword(), user.getHashedPassword());
        if (!matches) {
            return null;
        }
        String token = jwtUtil.generateToken(userMapper.toModel(user));
        return TokenDto.builder().token(token).expiredAt(jwtUtil.tokenExpiration()).generatedAt(LocalDateTime.now()).build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.map(UserInfoDetailsDto::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    @Override
    public User getObjForExample(UserDto user){
        return User.builder().email(user.getEmail()).username(user.getUsername()).build();
    }
}
