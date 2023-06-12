package com.liupanshui.educenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liupanshui.educenter.entity.LoginLog;
import com.liupanshui.educenter.entity.vo.LoginLogQuery;
import com.liupanshui.educenter.mapper.LoginLogMapper;
import com.liupanshui.educenter.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 登录日志表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-04-12
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService {


    @Autowired
    private LoginLogMapper loginLogMapper;


    @Override
    public List<LoginLog> getLoginLogList(LoginLogQuery query) {
        return null;
    }

    @Override
    public List<LoginLog> list(int startIndex, int size) {
        return null;
    }

}
