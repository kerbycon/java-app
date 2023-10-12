package com.example.demo.code;

import com.example.demo.user.User;
import jakarta.persistence.*;

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
