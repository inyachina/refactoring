package com.example.springbackend.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@Table
public class HumanOrder {
    @Id
    @GeneratedValue(generator = "increment")
    private int id;

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
    @JoinColumn(name="HUMAN_ID")
    private Human human;

    public HumanOrder(String status, int createdBy, Human human) {
        this.status = status;
        this.createdDate = Date.valueOf(LocalDate.now());
        this.modifiedDate = Date.valueOf(LocalDate.now());
        this.createdBy = createdBy;
        this.modifiedBy = createdBy;
        this.human = human;
    }

    public HumanOrder() {

    }
}
