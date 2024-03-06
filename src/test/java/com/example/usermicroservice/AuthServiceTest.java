package com.example.usermicroservice;

import com.example.usermicroservice.dto.UserDto;
import com.example.usermicroservice.exception.SessionNotFountError;
import com.example.usermicroservice.exception.UserNotFoundExceptions;
import com.example.usermicroservice.models.Session;
import com.example.usermicroservice.models.SessionStatus;
import com.example.usermicroservice.models.User;
import com.example.usermicroservice.repository.SessionRepository;
import com.example.usermicroservice.repository.UserRepository;
import com.example.usermicroservice.service.AuthServices;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Commit;

import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

@SpringBootTest
public class AuthServiceTest {
    @Inject
    private AuthServices authServices;
    @Inject
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Inject
    private UserRepository userRepository;


    @Test
    @Commit
    public void  signUp() {
        Scanner sc= new Scanner(System.in);
        String emailId="gopal";
        String password="gopal";
        User user=new User();
        user.setEmailId(emailId);
        user.setPassword(bCryptPasswordEncoder.encode(password));


       userRepository.save(user);
//        System.out.println(savedUser.getEmailId() +" "+ savedUser.getPassword());


//        return UserDto.convertToUserDto(user);
    }

@Test
@Commit
    public void changePassWord() throws UserNotFoundExceptions {
//        return userService.changeUserPassword(emailId,password);
        String emailId="gopal";
        String password="gopal";
        Optional<User> user=userRepository.findByEmailId(emailId);

        User currentUser=user.get();
    System.out.println(currentUser.getPassword().toString());
        if(user.isEmpty()){
            throw new  UserNotFoundExceptions("user not found");
        }

        currentUser.setPassword(bCryptPasswordEncoder.encode(password));
    System.out.println(currentUser.getPassword().toString());



    }

}
