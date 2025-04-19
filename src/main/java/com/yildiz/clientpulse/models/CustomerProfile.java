package com.yildiz.clientpulse.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_profile")
@Data
public class CustomerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Double churnScore;        // Örn: 0.85 gibi bir olasılık
    private Double loyaltyScore;      // Sadakat skoru: 0-1 arası
    private String segment;           // Örn: GOLD, SILVER, BRONZE
    private LocalDateTime lastActivity;

    @Column(columnDefinition = "TEXT")
    private String aiRecommendations; // OpenAI'den dönen öneri metni
}