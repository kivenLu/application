package com.alibaba.application.entity;

import lombok.Data;

@Data
public class Survey {
    private Integer id;
    private String question;
    private String answer;
    private Integer xlsId;

}
