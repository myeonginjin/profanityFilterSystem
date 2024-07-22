package com.study.profanityFilterSystem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "dialog")
public class Dialog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dialogID;

    @Column(name = "userID", nullable = false)
    private Long userID;

    @Column(name = "partnerUserID", nullable = false)
    private Long partnerUserID;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    // Getters and setters
    public Long getDialogID() {
        return dialogID;
    }

    public void setDialogID(Long dialogID) {
        this.dialogID = dialogID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getPartnerUserID() {
        return partnerUserID;
    }

    public void setPartnerUserID(Long partnerUserID) {
        this.partnerUserID = partnerUserID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
