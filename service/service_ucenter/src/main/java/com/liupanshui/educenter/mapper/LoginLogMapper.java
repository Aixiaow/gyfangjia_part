package com.liupanshui.educenter.mapper;

import com.liupanshui.educenter.entity.LoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 登录日志表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2023-04-12
 */
public interface LoginLogMapper extends BaseMapper<LoginLog> {


    List<LoginLog> list(int offset, int size);

}
