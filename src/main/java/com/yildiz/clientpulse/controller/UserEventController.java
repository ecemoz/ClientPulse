package com.yildiz.clientpulse.controller;

import com.yildiz.clientpulse.models.UserActionEvent;
import com.yildiz.clientpulse.service.KafkaProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class UserEventController {

    private final KafkaProducerService kafkaProducerService;

    public UserEventController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/track")
    public ResponseEntity<String> trackUserAction(@RequestBody UserActionEvent event) {
        kafkaProducerService.sendUserAction(event);
        return new ResponseEntity<>("Event sent to Kafka ✅", HttpStatus.OK);
    }
}
