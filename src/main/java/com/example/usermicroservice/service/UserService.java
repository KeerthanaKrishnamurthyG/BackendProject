package com.example.usermicroservice.service;


import com.example.usermicroservice.dto.UserDto;
import com.example.usermicroservice.exception.UserNotFoundExceptions;
import com.example.usermicroservice.models.Role;
import com.example.usermicroservice.models.SessionStatus;
import com.example.usermicroservice.models.User;

import com.example.usermicroservice.repository.RoleRepository;
import com.example.usermicroservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;


    public UserService(UserRepository userRepository, RoleRepository roleRepository) {

        this.userRepository = userRepository;
        this.roleRepository=roleRepository;
    }



    public SessionStatus validateSessiontoken(String token, String userName){
        return null;
    }

    public UserDto forgotUserPassword(String emailId) throws UserNotFoundExceptions{
        Optional<User> user=userRepository.findByEmailId(emailId);
        User currentUser=user.get();
        if(user.isEmpty()){
            throw new  UserNotFoundExceptions("user not found");
        }
        return null;



    }



    public UserDto setUserRole(String emailId, Set<String> userRole) throws UserNotFoundExceptions{
        Optional<User> user=userRepository.findByEmailId(emailId);
        List<Role> roles = roleRepository.findAllByIdIn(List.of(userRole.toString()));
        if (user.isEmpty()) {
            throw new UserNotFoundExceptions("User Not Found");
        }
        User currentUser=user.get();
        currentUser.setRoles( Set.copyOf(roles));
        userRepository.save(currentUser);
        return UserDto.convertToUserDto(currentUser);
    }

}
