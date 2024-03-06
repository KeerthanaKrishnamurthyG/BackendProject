package com.example.usermicroservice.repository;


import com.example.usermicroservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
   Optional<User>  findByEmailId(String emailId);
   Optional<User> deleteUserById(Long id);


}