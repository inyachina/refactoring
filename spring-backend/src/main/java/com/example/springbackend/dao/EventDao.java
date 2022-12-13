package com.example.springbackend.dao;

import lombok.Data;
import org.springframework.lang.Nullable;


@Data
public class EventDao {
    private String name;
    private String description;
    private double startTime;
    @Nullable
    private double endTime;
}
