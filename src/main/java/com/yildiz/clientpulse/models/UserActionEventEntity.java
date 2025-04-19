package com.yildiz.clientpulse.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_action_events")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserActionEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Column(nullable = false)
    private String actionType;

    @Column(nullable = false)
    private String metadata;

    @Column(nullable = false)
    private LocalDateTime timestamp;
}