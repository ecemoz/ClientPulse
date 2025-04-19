package com.yildiz.clientpulse.service;

import com.yildiz.clientpulse.models.UserActionEvent;
import com.yildiz.clientpulse.models.UserActionEventEntity;
import com.yildiz.clientpulse.repository.UserActionEventRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final UserActionEventRepository repository;

    public KafkaConsumerService(UserActionEventRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "${kafka.topic.user-events}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(UserActionEvent event) {
        System.out.println("🎯 Received event from Kafka: " + event);


        UserActionEventEntity entity = new UserActionEventEntity();
        entity.setUserId(event.getUserId());
        entity.setActionType(event.getActionType());
        entity.setMetadata(event.getMetadata());
        entity.setTimestamp(event.getTimestamp());

        repository.save(entity);
        System.out.println("✅ Event saved to PostgreSQL!");
    }
}

