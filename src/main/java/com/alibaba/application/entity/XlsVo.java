package com.alibaba.application.entity;

import lombok.Data;

import java.util.List;
@Data
public class XlsVo {
    private Integer code;
    private Integer count;
    private String msg;
    private List<Xls> data;
}
