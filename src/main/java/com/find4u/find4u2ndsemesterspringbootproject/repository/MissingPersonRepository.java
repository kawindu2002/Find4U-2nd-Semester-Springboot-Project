package com.find4u.find4u2ndsemesterspringbootproject.repository;

import com.find4u.find4u2ndsemesterspringbootproject.entity.MissingPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MissingPersonRepository extends JpaRepository<MissingPerson, Long> {
     
     @Transactional
     @Modifying
     @Query(value = "UPDATE missing_person SET status = ?2 WHERE id = ?1", nativeQuery = true)
     void updateMissingPersonStatusById(Long id, String newStatus);

}

