package com.example.usermicroservice.repository;

import com.example.usermicroservice.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {


    Optional<Session> findSessionByToken(String token);

    @Transactional
    @Modifying
    @Query("delete from Session s")
    void deleteFirstBy();
}