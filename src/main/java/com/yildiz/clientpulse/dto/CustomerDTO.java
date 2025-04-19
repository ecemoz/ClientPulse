package com.yildiz.clientpulse.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
}