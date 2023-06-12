package com.liupanshui.educenter.service.impl;

import com.alibaba.nacos.client.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liupanshui.educenter.entity.OperationLog;
import com.liupanshui.educenter.entity.vo.OperationLogQuery;
import com.liupanshui.educenter.mapper.OperationLogMapper;
import com.liupanshui.educenter.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2023-04-12
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {


    @Autowired
    private OperationLogMapper operationLogMapper;
    @Override
    public List<OperationLog> getOperationLogList(OperationLogQuery query) {
        QueryWrapper<OperationLog> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(query.getOperator()), "operator", query.getOperator())
                .eq(StringUtils.isNotBlank(query.getAction()), "action", query.getAction())
                .orderByDesc("create_time");
        return operationLogMapper.selectList(wrapper);
    }
}
