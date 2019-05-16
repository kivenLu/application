package com.alibaba.application.entity;

import lombok.Data;

@Data
public class XlsInfo {
    private Integer page;
    private Integer limit;
    private Integer id;
    private String name;
    private String password;
    private String question;
    private String answer;
    private Integer power;
    private String xlsName;
}
