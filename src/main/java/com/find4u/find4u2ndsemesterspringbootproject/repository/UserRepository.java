package com.find4u.find4u2ndsemesterspringbootproject.repository;

import com.find4u.find4u2ndsemesterspringbootproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     
     @Transactional
     @Modifying
     @Query(value = "UPDATE user SET status = ?2 WHERE id = ?1", nativeQuery = true)
     void updateUserStatusById(Long id, String newStatus);
     
     Optional<User> findByEmail(String email);
     
     boolean existsByEmail(String email);
     
}


