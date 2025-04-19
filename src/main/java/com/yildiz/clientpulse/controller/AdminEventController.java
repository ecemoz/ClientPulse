package com.yildiz.clientpulse.controller;

import com.yildiz.clientpulse.models.UserActionEventEntity;
import com.yildiz.clientpulse.service.UserActionEventService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/events")
@SecurityRequirement(name = "BearerAuth")
public class AdminEventController {

    private final UserActionEventService eventService;

    public AdminEventController(UserActionEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<UserActionEventEntity> getEvents(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            return eventService.getEventsByUserId(userId);
        }
        return eventService.getAllEvents();
    }

    @GetMapping("/stats")
    public Map<String, Long> getEventStats() {
        return eventService.getEventStats();
    }
}