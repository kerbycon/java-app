package com.example.demo.code;

import com.example.demo.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String code;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Code() {

    }

    public Code(String code, User user) {
        this.code = code;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
}
