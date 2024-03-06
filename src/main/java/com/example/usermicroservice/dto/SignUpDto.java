package com.example.usermicroservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDto {
    private String emailId;
    private String  password;
//    private Set<UserRole> userRole;
//    private Long mobileNumber;
}

