package com.yildiz.clientpulse.service;

import com.yildiz.clientpulse.models.CustomerProfile;
import com.yildiz.clientpulse.models.User;

import java.util.List;

public interface CustomerProfileService {
    CustomerProfile createProfileForUser(User user);
    List<CustomerProfile> getAllProfiles();
    CustomerProfile getProfileByUserId(Long userId);
}
