package com.alibaba.application.entity;

import lombok.Data;

import java.util.Map;
@Data
public class Info {
    private int code;
    private String msg;
    private Map<String,String> data;
    public final static String ERROR =  "data error";
    public final static String SUCCESS =  "data success";
    public final static int SUCCESS_CODE =  0;
}
