package com.find4u.find4u2ndsemesterspringbootproject.repository;

import com.kp.find4uspringbootproject.entity.MissingTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissingTipRepository extends JpaRepository<MissingTip, Long> {

}

