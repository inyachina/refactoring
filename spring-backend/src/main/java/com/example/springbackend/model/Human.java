package com.example.springbackend.model;


import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table
public class Human {
    @Id
    @GeneratedValue(generator = "increment")
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private Date birthdayDate;

    @Column(nullable = false)
    private float time;

    @Column(nullable = false)
    private int userId;
    @Nullable
    private String fate;

    @OneToOne
    private HumanOrder humanOrder;

    public Human(String name, String surname, Date birthdayDate, int userId, float time, @Nullable String fate) {
        this.name = name;
        this.surname = surname;
        this.birthdayDate = birthdayDate;
        this.userId = userId;
        this.time = time;
        this.fate = fate;
    }

    public Human() {
    }
}
