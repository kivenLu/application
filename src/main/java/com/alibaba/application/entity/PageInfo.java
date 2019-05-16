package com.alibaba.application.entity;

import lombok.Data;

@Data
public class PageInfo {
    private Integer page;
    private Integer limit;
    private String name;
}
