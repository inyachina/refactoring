package com.example.springbackend.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Entity
public class MOR {

    @Id
    @GeneratedValue(generator = "increment")
    int id;

    @Column(nullable = false)
    private float successPercent;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date createdDate;

    @Column(nullable = false)
    private Date modifiedDate;

    @Column(nullable = false)
    private int createdBy;

    @Column(nullable = false)
    private int modifiedBy;

    @ManyToMany
    private List<Event> events;

    public MOR(float successPercent, String description, Date createdDate, Date modifiedDate, int createdBy, int modifiedBy, List<Event> events) {
        this.successPercent = successPercent;
        this.description = description;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.events = events;
    }

    public MOR( ) {
    }
}
