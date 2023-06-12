package com.liupanshui.serviceedu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AllDataQuery {
    @ApiModelProperty(value = "行政区, 模糊查询")
    private String district;
    @ApiModelProperty(value = "地址, 模糊查询")
    private String idtyId;
    @ApiModelProperty(value = "幼儿园, 周边幼儿园数")
    private Integer mumber_kindergartens;
}
