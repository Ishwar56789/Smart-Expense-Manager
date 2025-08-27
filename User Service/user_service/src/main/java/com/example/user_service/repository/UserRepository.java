package com.example.user_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.user_service.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
        value = "SELECT * FROM user_data_table WHERE user_email_address = :userEmail",
        nativeQuery = true
    )
    Optional<User> findByEmailAddress(@Param("userEmail") String userEmail);

}
