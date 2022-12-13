package com.example.springbackend.model;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table
public class EventOrder {
    @Id
    @GeneratedValue(generator = "increment")
    int id;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Date createdDate;

    @Column(nullable = false)
    private Date modifiedDate;

    @Column(nullable = false)
    private int createdBy;

    @Column(nullable = false)
    private int modifiedBy;

    @OneToOne
    private Event event;

    @ManyToMany
    private List<MOR> MORs;

    public EventOrder(String status, Date createdDate, Date modifiedDate, int createdBy, int modifiedBy, Event event, List<MOR> MORs) {
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.event = event;
        this.MORs = MORs;
    }

    public EventOrder(){

    }
}