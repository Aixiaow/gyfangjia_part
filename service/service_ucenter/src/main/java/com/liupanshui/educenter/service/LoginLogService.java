package com.liupanshui.educenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liupanshui.educenter.entity.LoginLog;
import com.liupanshui.educenter.entity.vo.LoginLogQuery;

import java.util.List;

/**
 * <p>
 * 登录日志表 服务类
 * </p>
 *
 * @author testjava
 * @since 2023-04-12
 */
public interface LoginLogService extends IService<LoginLog> {

    List<LoginLog> getLoginLogList(LoginLogQuery query);

    List<LoginLog> list(int startIndex, int size);

}
