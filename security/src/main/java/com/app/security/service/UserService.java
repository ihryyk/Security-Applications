package com.app.security.service;

import com.app.security.converter.UserMapper;
import com.app.security.dto.UserDto;
import com.app.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void save(UserDto userDto) {
        userRepository.save(userMapper.toUser(userDto));
    }

}
