package com.example.springbackend.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@Table
public class ProductOrder {
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

    @Column(nullable = false)
    private int productId;

    @Column(nullable = false)
    private int fromUser;

    @Column(nullable = false)
    private int toUser;

    @Column(nullable = false)
    private double fromTime;

    @Column(nullable = false)
    private double toTime;

    public ProductOrder() {
    }

    public ProductOrder(String status, int createdBy,int productId, int toUser, double fromTime, double toTime) {
        this.createdDate = Date.valueOf(LocalDate.now());
        this.modifiedDate = Date.valueOf(LocalDate.now());
        this.createdBy = createdBy;
        this.modifiedBy = createdBy;
        this.productId = productId;
        this.fromUser = createdBy;
        this.toUser = toUser;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.status = status;
    }
}
