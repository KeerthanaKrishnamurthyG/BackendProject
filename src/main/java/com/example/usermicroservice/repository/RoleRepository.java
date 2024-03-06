package com.example.usermicroservice.repository;


import com.example.usermicroservice.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    List<Role> findAllByIdIn(List<String> roleIds);

}
