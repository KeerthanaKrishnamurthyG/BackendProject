package com.example.usermicroservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RoleDto {
    private String emailId;
    private Set<String> userRole=new HashSet<>();


}
