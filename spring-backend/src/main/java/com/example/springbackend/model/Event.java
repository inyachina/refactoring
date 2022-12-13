package com.example.springbackend.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table
public class Event {

    @Id
    @GeneratedValue(generator = "increment")
    int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date createdDate;

    @Column(nullable = false)
    private double startTime;

    @Nullable
    private double endTime;


    public Event(String name, String description, Date created_date, double startTime, double endTime) {
        this.name = name;
        this.description = description;
        this.createdDate = created_date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(){}
}
