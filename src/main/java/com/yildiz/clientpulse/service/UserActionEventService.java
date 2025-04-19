package com.yildiz.clientpulse.service;

import com.yildiz.clientpulse.models.UserActionEventEntity;
import com.yildiz.clientpulse.repository.UserActionEventRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Long> getEventStats() {
        List<Object[]> results = eventRepository.countEventsByType();
        Map<String, Long> stats = new HashMap<>();
        for (Object[] result : results) {
            String type = result[0].toString();
            Long count = (Long) result[1];
            stats.put(type, count);
        }
        return stats;
    }
}
