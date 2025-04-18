package com.yildiz.clientpulse.models;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserActionEvent {

    private Long userId;
    private String actionType;
    private String metadata;
    private LocalDateTime timestamp;
}