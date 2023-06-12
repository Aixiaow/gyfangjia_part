package com.liupanshui.educenter.entity.vo;

import lombok.Data;

@Data
public class OperationLogQuery {
    private String operator;
    private String action;
    private Integer pageNum;
    private Integer pageSize;
}