package com.study.profanityFilterSystem.repository;

import com.study.profanityFilterSystem.entity.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DialogRepository extends JpaRepository<Dialog, Long> {
}
