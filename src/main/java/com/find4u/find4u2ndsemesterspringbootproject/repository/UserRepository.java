package com.find4u.find4u2ndsemesterspringbootproject.repository;

import com.find4u.find4u2ndsemesterspringbootproject.entity.User;
import com.find4u.find4u2ndsemesterspringbootproject.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
     
     long countByIdStartingWith(Long id);
     List<User> findUserByIdContainingIgnoreCase(String keyword);
     
     @Transactional
     @Modifying
     @Query(value = "UPDATE user SET status = ?2 WHERE id = ?1", nativeQuery = true)
     void updateUserStatusById(String id, UserStatus newStatus);
     
     Optional<User> findByEmail(String email);
     Optional<User> findByVerificationToken(String verificationToken);
     boolean existsByEmail(String email);
     
}


//import java.util.Optional;
//
//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByEmail(String email);
//    Optional<User> findByVerificationToken(String verificationToken);
//    boolean existsByEmail(String email);
//}