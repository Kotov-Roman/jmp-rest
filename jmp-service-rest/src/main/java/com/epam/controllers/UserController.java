package com.epam.controllers;

import com.epam.UserRequestDto;
import com.epam.UserResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {


//    public UserResponseDto createUser(UserRequestDto userRequestDto) {
//
//        return null;
//    }
//
//    public UserResponseDto updateUser(UserRequestDto userRequestDto) {
//
//        return null;
//    }
//
//    public void deleteUser(Long id) {
//
//    }
//
//    @GetMapping
//    public UserResponseDto getUser(Long id) {
//
//        return null;
//    }
//
//    @GetMapping
//    public List<UserResponseDto> getAllUsers() {
//
//        return null;
//    }

    @GetMapping
    public String get() {

        return "hello";
    }

    @GetMapping("/kek")
    public String get2() {

        return "kek";
    }

}

