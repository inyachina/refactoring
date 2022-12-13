package com.example.springbackend.data.dto;


import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private Integer ownerId;
    private Float timeCurrent;
}
