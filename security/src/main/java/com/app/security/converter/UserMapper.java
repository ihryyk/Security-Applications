package com.app.security.converter;

import com.app.security.dto.UserDto;
import com.app.security.model.Role;
import com.app.security.model.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User toUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = new Role();
        role.setName(userDto.getRole());
        user.setRoles(List.of(role));
        return user;
    }


}
