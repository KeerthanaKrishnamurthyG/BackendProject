package com.example.usermicroservice.dto;

import com.example.usermicroservice.models.Role;
import com.example.usermicroservice.models.User;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String emailId;
//    private String password;
//    private Long mobileNumber;
    private Set<Role> userRole;


    public static UserDto convertToUserDto(User user) {
        UserDto userDto=new UserDto();
        userDto.setEmailId(user.getEmailId());
   userDto.setUserRole(user.getRoles());
//        userDto.setMobileNumber(user.getMobileNumber());
        return userDto;
    }
}
