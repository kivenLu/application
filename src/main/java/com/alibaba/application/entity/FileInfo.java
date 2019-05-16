package com.alibaba.application.entity;

import lombok.Data;

import java.util.Map;
@Data
public class FileInfo {
    private int code;
    private String name;
    private Map<String,String> data;
    public final static int SUCCESS_CODE =  1;
    public final static int FAIL_CODE =  0;
    public final static int ERROR_CODE =  -1;

}