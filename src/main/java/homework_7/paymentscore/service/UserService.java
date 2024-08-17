package homework_7.paymentscore.service;

import homework_7.paymentscore.dto.UserDto;
import homework_7.paymentscore.dto.UserResponseDto;
import homework_7.paymentscore.exception.CustomNotFoundException;
import homework_7.paymentscore.mapper.UserMapper;
import homework_7.paymentscore.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto getUser(Long id) {
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() ->
                        new CustomNotFoundException("Пользователь с id = " + id + " не существует", HttpStatus.NOT_FOUND)));
    }

    public UserResponseDto getAllUsers() {
        List<UserDto> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(userMapper.toDto(user)));
        return new UserResponseDto(users.size(), users);
    }
}
