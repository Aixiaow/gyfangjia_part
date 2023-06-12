package com.liupanshui.serviceedu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class NewPriceQuery {
    @ApiModelProperty(value = "行政区, 模糊查询")
    private String district;
    @ApiModelProperty(value = "商业区 ，模糊查询 ")
    private String village;

}
