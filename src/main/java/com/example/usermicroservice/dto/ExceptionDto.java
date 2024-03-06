package com.example.usermicroservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter

public class ExceptionDto {
    private   String message;
    private HttpStatus httpStatus;

}
