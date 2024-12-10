package com.api.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "action_history")
public class ActionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento ManyToOne com User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String action;
    private String target;
    private String details;
    private LocalDateTime timestamp;

    // Quando a ação é registrada, define o timestamp
    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
    }
}
