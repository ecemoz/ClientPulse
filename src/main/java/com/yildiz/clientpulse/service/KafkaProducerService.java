package com.yildiz.clientpulse.service;

import com.yildiz.clientpulse.models.UserActionEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, UserActionEvent> kafkaTemplate;

    @Value("${kafka.topic.user-events}")
    private String topic;

    public KafkaProducerService(KafkaTemplate<String, UserActionEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserAction(UserActionEvent event) {
        kafkaTemplate.send(topic, event);
        System.out.println("✅ Event sent: " + event);
    }
}