package com.liupanshui.serviceedu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author testjava
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="AllData对象", description="房价")
public class AllData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "贵阳房价数据ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    private String district;

    private String village;

    private String price;

    private String idtyId;

    private String lng;

    private String lat;

    private String propertyType;

    private String opCategory;

    private String completionTime;

    private String households;

    private Integer plotRatio;

    private String greeningRate;

    private String buildingType;

    private String businessDistrict;

    private String parkingRate;

    private String propertyFee;

    private Integer numberSubway;

    private Integer mumberKindergartens;

    private Integer numberSchools;

    private Integer numberSupermarkets;


}
