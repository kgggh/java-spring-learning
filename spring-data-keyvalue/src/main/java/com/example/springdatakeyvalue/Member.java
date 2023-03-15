package com.example.springdatakeyvalue;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import java.util.UUID;

@Data
@KeySpace("members")
public class Member {
    @Id
    private String id;
    private String name;
    private Gender gender;

    @Getter
    enum Gender {
        FEMALE, MALE
    }

    public Member(String name, Gender gender) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.gender = gender;
    }

    public static Member newInstance(String name, Gender gender) {
        return new Member(name, gender);
    }
}
