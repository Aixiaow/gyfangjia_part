package com.liupanshui.educenter.entity.vo;

import lombok.Data;

@Data
public class LoginLogQuery {
    private String username;
    private String ip;
    private Integer pageNum;
    private Integer pageSize;
}