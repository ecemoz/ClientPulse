package com.yildiz.clientpulse.service.impl;

import com.yildiz.clientpulse.models.CustomerProfile;
import com.yildiz.clientpulse.models.User;
import com.yildiz.clientpulse.repository.CustomerProfileRepository;
import com.yildiz.clientpulse.repository.UserRepository;
import com.yildiz.clientpulse.service.CustomerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerProfileServiceImpl implements CustomerProfileService {

    private final CustomerProfileRepository repository;
    private final UserRepository userRepository;

    @Override
    public CustomerProfile createProfileForUser(User user) {
        if (repository.findByUser(user).isPresent()) {
            return repository.findByUser(user).get();
        }

        CustomerProfile profile = new CustomerProfile();
        profile.setUser(user);
        profile.setChurnScore(0.0);
        profile.setLoyaltyScore(0.0);
        profile.setSegment("NEW");
        profile.setLastActivity(LocalDateTime.now());
        profile.setAiRecommendations("Hoş geldiniz! Henüz öneriniz yok.");

        return repository.save(profile);
    }

    @Override
    public List<CustomerProfile> getAllProfiles() {
        return repository.findAll();
    }

    @Override
    public CustomerProfile getProfileByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return repository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Customer profile not found"));
    }
}

