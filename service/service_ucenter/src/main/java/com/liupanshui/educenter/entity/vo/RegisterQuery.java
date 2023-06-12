package com.liupanshui.educenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegisterQuery {
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "性别 1 女，2 男")
    private Integer sex;
    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
