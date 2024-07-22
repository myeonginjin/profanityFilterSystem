package com.study.profanityFilterSystem.repository;

import com.study.profanityFilterSystem.entity.Dialog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DialogRepository extends JpaRepository<Dialog, Long> {
    List<Dialog> findByUserIDAndTimestampAfter(Long userID, LocalDateTime timestamp);
}
