package com.yildiz.clientpulse.repository;

import com.yildiz.clientpulse.models.CustomerProfile;
import com.yildiz.clientpulse.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerProfileRepository extends JpaRepository<CustomerProfile, Long> {
    Optional<CustomerProfile> findByUser(User user);
}
