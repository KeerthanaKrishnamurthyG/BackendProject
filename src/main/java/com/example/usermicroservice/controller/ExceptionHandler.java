package com.example.usermicroservice.controller;

import com.example.usermicroservice.dto.ExceptionDto;
import com.example.usermicroservice.exception.UserNotFoundExceptions;
import com.example.usermicroservice.exception.WrongPassWord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {


   @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundExceptions.class)
    public ResponseEntity<ExceptionDto> userNotFound(UserNotFoundExceptions userNotFoundExceptions){
        ExceptionDto exceptionDto=new ExceptionDto();
        exceptionDto.setHttpStatus(HttpStatus.BAD_REQUEST);
        exceptionDto.setMessage(userNotFoundExceptions.getMessage());
        return  new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);

    }
    @org.springframework.web.bind.annotation.ExceptionHandler(WrongPassWord.class)
    public ResponseEntity<ExceptionDto> wrongPassword(WrongPassWord wrongPassWord){
        ExceptionDto exceptionDto=new ExceptionDto();
        exceptionDto.setHttpStatus(HttpStatus.BAD_REQUEST);
        exceptionDto.setMessage(wrongPassWord.getMessage());
        return  new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);

    }
    @org.springframework.web.bind.annotation.ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionDto> customUserNotFound(UsernameNotFoundException usernameNotFoundException){
       ExceptionDto exceptionDto=new ExceptionDto();
       exceptionDto.setMessage("User not found in registry");
       exceptionDto.setHttpStatus(HttpStatus.BAD_REQUEST);
       return new ResponseEntity<>(exceptionDto,HttpStatus.BAD_REQUEST);

    }
}
