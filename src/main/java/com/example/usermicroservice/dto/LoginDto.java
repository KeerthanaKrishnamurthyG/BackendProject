package com.example.usermicroservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String emailId;
    private String password;
}
