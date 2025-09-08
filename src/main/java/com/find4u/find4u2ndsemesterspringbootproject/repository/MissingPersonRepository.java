package com.find4u.find4u2ndsemesterspringbootproject.repository;

import com.find4u.find4u2ndsemesterspringbootproject.entity.MissingPerson;
import com.find4u.find4u2ndsemesterspringbootproject.enums.PersonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MissingPersonRepository extends JpaRepository<MissingPerson, String> {

//   SELECT COUNT(*) FROM missing_person WHERE id LIKE 'MIS-2025-%'
     long countByIdStartingWith(String prefix);
     
     List<MissingPerson> findMissingPersonByIdContainingIgnoreCase(String keyword);
     
     @Transactional
     @Modifying
     @Query(value = "UPDATE missing_person SET status = ?2 WHERE id = ?1", nativeQuery = true)
     void updateMissingPersonStatusById(String id, PersonStatus newStatus);

}

