package com.example.springbackend.data.dto;

import lombok.Data;

@Data
public class ProductOrderDTO {
    private Integer productId;
    private String toUser;
    private Float fromTime;
    private Float toTime;
}
