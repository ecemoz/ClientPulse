package com.yildiz.clientpulse.service;

import com.yildiz.clientpulse.models.UserActionEventEntity;
import com.yildiz.clientpulse.repository.UserActionEventRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserActionEventService {

    private final UserActionEventRepository eventRepository;

    public UserActionEventService(UserActionEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<UserActionEventEntity> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<UserActionEventEntity> getEventsByUserId(Long userId) {
        return eventRepository.findByUserId(userId);
    }
}
