package org.example.scheduleforuser.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleforuser.dto.LoginRequestDto;
import org.example.scheduleforuser.entity.User;
import org.example.scheduleforuser.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User Login(LoginRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(()-> new IllegalArgumentException("이메일 또는 비밀번호가 다릅니다."));

        if (!user.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다");
        }
        return user;
    }
}
