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
    public User Login(LoginRequestDto requestDto) { //사용자 이메일로 조회 후 비밀번호 일치 여부 확인
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(()-> new IllegalArgumentException("이메일이 다릅니다."));

        if (!user.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 다릅니다.");
        }
        return user;
    }
}
