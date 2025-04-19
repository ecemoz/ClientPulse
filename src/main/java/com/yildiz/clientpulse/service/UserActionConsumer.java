package com.yildiz.clientpulse.service;

import com.yildiz.clientpulse.models.UserActionEventEntity;
import com.yildiz.clientpulse.models.UserActionEvent;
import com.yildiz.clientpulse.repository.UserActionEventRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserActionConsumer {

    private final UserActionEventRepository repository;

    public UserActionConsumer(UserActionEventRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "user-events", groupId = "clientpulse-group")
    public void consume(UserActionEvent event) {
        UserActionEventEntity entity = new UserActionEventEntity();
        entity.setUserId(event.getUserId());
        entity.setActionType(event.getActionType());
        entity.setMetadata(event.getMetadata());
        entity.setTimestamp(event.getTimestamp());

        repository.save(entity);
        System.out.println("✅ Event saved to PostgreSQL!");
    }
}