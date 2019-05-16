package com.alibaba.application.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String password;
    private String question;
    private String answer;
    private Integer power;
}
