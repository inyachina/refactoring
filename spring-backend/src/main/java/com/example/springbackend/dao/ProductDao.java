package com.example.springbackend.dao;


import lombok.Data;

@Data
public class ProductDao {
    private String name;
    private String description;
    private int owner;
    private float timeCurrent;
}
