package com.example.springbackend.data.dto;

import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class EventOrderDTO {
    private Integer eventId;
    private String status;
}
