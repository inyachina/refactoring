package com.example.springbackend.data.dto;

import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.sql.Date;

@Data
@NonNull
public class HumanDTO {
    private String name;
    private String surname;
    private Date birthdayDate;
    @Nullable
    private String fate;
    private Float time;
}
