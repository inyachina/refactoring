package com.example.springbackend.dao;

import lombok.Data;

@Data
public class ProductOrderDao {
    private int productId;
    private String toUser;
    private float fromTime;
    private float toTime;
}
