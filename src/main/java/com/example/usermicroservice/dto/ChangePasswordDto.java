package com.example.usermicroservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDto {
    private String emailId;
    private String password;
}
