package com.example.springtdd.natural;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
public class Member {
    private String name;
    private int age;
    private boolean isAdult;
    private Gender gender;
    private LocalDate birthday;

    @Getter
    public enum Gender {
        MALE,
        FEMALE
    }
}
