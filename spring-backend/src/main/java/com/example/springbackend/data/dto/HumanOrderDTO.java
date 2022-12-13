package com.example.springbackend.data.dto;

import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class HumanOrderDTO {
    private Integer id;
    private String status;
}
