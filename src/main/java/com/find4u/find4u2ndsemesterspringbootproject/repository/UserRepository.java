package com.find4u.find4u2ndsemesterspringbootproject.repository;

import com.kp.find4uspringbootproject.entity.User;
import com.kp.find4uspringbootproject.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
     
     //   SELECT COUNT(*) FROM missing_person WHERE id LIKE 'USR-2025-%'
     long countByIdStartingWith(String prefix);
     
     List<User> findUserByIdContainingIgnoreCase(String keyword);
     
     @Transactional
     @Modifying
     @Query(value = "UPDATE user SET status = ?2 WHERE id = ?1", nativeQuery = true)
     void updateUserStatusById(String id, UserStatus newStatus);
}

