package org.example.scheduleforuser.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleforuser.dto.UserRequestDto;
import org.example.scheduleforuser.dto.UserResponseDto;
import org.example.scheduleforuser.entity.User;
import org.example.scheduleforuser.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users/createUser")
    public UserResponseDto createUser(@RequestBody UserRequestDto requestDto) {
        return userService.save(requestDto);
    }

    @GetMapping("/users/allUser")
    public List<UserResponseDto> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/findOneUser/{id}")
    public UserResponseDto findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PutMapping("/users/updateUser/{id}")
    public UserResponseDto updateUser(@PathVariable Long id, @RequestBody UserRequestDto requestDto) {
        return userService.update(id, requestDto);
    }

    @DeleteMapping("/users/deleteUser/{id}")
    public String deleteUserById(@PathVariable Long id) {
        userService.delete(id);
        return "삭제 성공";
    }

}
