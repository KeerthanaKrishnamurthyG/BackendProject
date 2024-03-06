package com.example.usermicroservice.controller;

import com.example.usermicroservice.dto.*;
import com.example.usermicroservice.exception.InvalidTokenException;
import com.example.usermicroservice.exception.SessionNotFountError;
import com.example.usermicroservice.exception.UserNotFoundExceptions;
import com.example.usermicroservice.exception.WrongPassWord;
import com.example.usermicroservice.models.Session;
import com.example.usermicroservice.models.SessionStatus;
import com.example.usermicroservice.service.AuthServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/OAuth")
public class AuthController {
   private AuthServices authServices;


    public  AuthController(AuthServices authServices){
        this.authServices=authServices;
    }
    @PostMapping("/signUp")
    public ResponseEntity<UserDto> createUser(@RequestBody SignUpDto userSignUp){

        UserDto userDto=authServices.signUp(userSignUp.getEmailId(),userSignUp.getPassword());
        System.out.println(userDto.getEmailId());

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/Login")
    public  ResponseEntity<UserDto> userLogin(@RequestBody LoginDto userLogin) throws UserNotFoundExceptions, WrongPassWord {
        return authServices.login(userLogin.getEmailId(),userLogin.getPassword());

    }
@PostMapping("/LogOut")
    public ResponseEntity<Void> userLogout(@RequestBody LogOutDto logOutDto) throws InvalidTokenException, UserNotFoundExceptions {
        return authServices.userLogout(logOutDto.getEmailId(), logOutDto.getToken());
    }



    @PostMapping("/ChangePassWord")
    @PreAuthorize("hasRole('Profile')")
    public  ResponseEntity<UserDto> changePassWord(@RequestBody ChangePasswordDto changePasswordDto) throws UserNotFoundExceptions {
        UserDto userDto=authServices.changePassWord(changePasswordDto.getEmailId(),changePasswordDto.getPassword());
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
    @PostMapping("/ForgotPassWord")
    public  ResponseEntity<UserDto> forgotPassWord(@RequestBody ForgotPasswordDto forgotPassword) throws UserNotFoundExceptions {
        UserDto userDto=authServices.forgotUserPassword(forgotPassword.getEmailId());
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @GetMapping("/validate/{authtoken}")
    public ResponseEntity<SessionStatus> validateToken(@PathVariable ( value = "authtoken") String token) throws SessionNotFountError {
        return new ResponseEntity<>(authServices.validate(token),HttpStatus.OK);
    }

}
