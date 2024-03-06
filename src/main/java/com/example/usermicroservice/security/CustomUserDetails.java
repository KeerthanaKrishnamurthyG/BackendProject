package com.example.usermicroservice.security;

import com.example.usermicroservice.models.Role;
import com.example.usermicroservice.models.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@JsonDeserialize(as= CustomUserDetails.class)
public class CustomUserDetails implements UserDetails {
    private User user;


//    json suggestion
    public CustomUserDetails(){}

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<Role> userRoles = user.getRoles();
        Collection<CustomGrantedAuthority> customGrantedAuthorityCollections = new ArrayList<>();
        for (Role i : userRoles) {
            customGrantedAuthorityCollections.add(new CustomGrantedAuthority(i));
        }


        return customGrantedAuthorityCollections;
    }


    @Override
    @JsonIgnore
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return user.getEmailId();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
