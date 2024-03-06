package com.example.usermicroservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;



import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonDeserialize(as= User.class)

public class User extends BaseModel {
    @Column(name="email_IDs")
    private String emailId;
    private String password;
    private Long mobileNumber;
    @ManyToMany(fetch = jakarta.persistence.FetchType.EAGER)
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();





}
