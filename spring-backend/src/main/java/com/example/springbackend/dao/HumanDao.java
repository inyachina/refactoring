package com.example.springbackend.dao;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.sql.Date;

@Data
public class HumanDao {
    String name;
    String surname;
    Date birthdayDate;
    @Nullable
    String fate;
    float time;
}
