package org.example.scheduleforuser.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.scheduleforuser.dto.LoginRequestDto;
import org.example.scheduleforuser.entity.User;
import org.example.scheduleforuser.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        try {
            User user = loginService.Login(loginRequestDto);
            response.addCookie(new jakarta.servlet.http.Cookie("sessionId", String.valueOf(user.getId())));
            return ResponseEntity.ok("로그인 성공");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패" + e.getMessage());
        }
    }

}
