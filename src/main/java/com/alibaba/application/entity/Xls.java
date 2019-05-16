package com.alibaba.application.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Xls {
    private Integer id;
    private String xlsName;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date date;
    private Integer userId;
}
