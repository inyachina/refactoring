package com.example.springbackend.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@Table
public class Product {

    @Id
    @GeneratedValue(generator = "increment")
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Date createdDate;

    @Column(nullable = false)
    private int owner;

    @Column(nullable = false)
    private boolean sold;

    @Column(nullable = false)
    private float timeCurrent;

    public Product(String name, String description,  int owner, float timeCurrent) {
        this.name = name;
        this.description = description;
        this.createdDate = Date.valueOf(LocalDate.now());
        this.owner = owner;
        this.sold = false;
        this.timeCurrent = timeCurrent;
    }

    public Product( ) {
    }
}
