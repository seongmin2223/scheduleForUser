package org.example.scheduleforuser.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleforuser.config.PasswordEncoder;
import org.example.scheduleforuser.dto.UserRequestDto;
import org.example.scheduleforuser.dto.UserResponseDto;
import org.example.scheduleforuser.entity.User;
import org.example.scheduleforuser.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto save(UserRequestDto requestDto) {
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(
                requestDto.getUsername(),
                requestDto.getEmail(),
                encodedPassword
        );
        userRepository.save(user);
        return new UserResponseDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDto = new ArrayList<>();
        for (User user : users) {
            userResponseDto.add(new UserResponseDto(user));
        }
        return userResponseDto;
    }

    @Transactional(readOnly = true)
    public UserResponseDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("해당 ID의 유저가 존재하지않습니다. : " + id)
        );
        return new UserResponseDto(user);
    }

    @Transactional
    public UserResponseDto update(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("해당 ID의 유저가 존재하지않습니다. : " + id)
        );
        user.update(requestDto);
        return new  UserResponseDto(user);
    }

    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("해당 ID의 유저가 존재하지않습니다. : " + id)
        );
        userRepository.delete(user);
    }
}
