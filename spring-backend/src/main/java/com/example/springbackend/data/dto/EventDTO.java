package com.example.springbackend.data.dto;

import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;


@Data
@NonNull
public class EventDTO {
    private String name;
    private String description;
    private Double startTime;
    @Nullable
    private Double endTime;
}
