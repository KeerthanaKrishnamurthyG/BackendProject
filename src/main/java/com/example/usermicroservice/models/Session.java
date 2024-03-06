package com.example.usermicroservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Session extends BaseModel{
    @ManyToOne
    private User username;

    @Enumerated(EnumType.ORDINAL)
    private SessionStatus sessionStatus;

    private String token;
    private Date createdAT;
    private Date expiredAt;

}
