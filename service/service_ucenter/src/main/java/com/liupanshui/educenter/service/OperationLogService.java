package com.liupanshui.educenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liupanshui.educenter.entity.OperationLog;
import com.liupanshui.educenter.entity.vo.OperationLogQuery;

import java.util.List;

/**
 * <p>
 * 操作日志表 服务类
 * </p>
 *
 * @author testjava
 * @since 2023-04-12
 */
public interface OperationLogService extends IService<OperationLog> {

    List<OperationLog> getOperationLogList(OperationLogQuery query);
}
