package com.find4u.find4u2ndsemesterspringbootproject.repository;

import com.find4u.find4u2ndsemesterspringbootproject.entity.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {

}

