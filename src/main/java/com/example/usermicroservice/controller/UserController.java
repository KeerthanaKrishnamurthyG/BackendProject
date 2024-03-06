package com.example.usermicroservice.controller;

import com.example.usermicroservice.dto.RoleDto;
import com.example.usermicroservice.dto.UserDto;
import com.example.usermicroservice.exception.UserNotFoundExceptions;
import com.example.usermicroservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userdetails")
public class UserController {
    private UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/setUserRole")
    public ResponseEntity<UserDto> setUserRole(@RequestBody RoleDto roleDto) throws UserNotFoundExceptions {
        UserDto userDto=userService.setUserRole(roleDto.getEmailId(),roleDto.getUserRole());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
