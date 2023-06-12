package com.liupanshui.educenter.controller;


import com.alibaba.nacos.client.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liupanshui.commonutils.R;
import com.liupanshui.educenter.entity.LoginLog;
import com.liupanshui.educenter.entity.OperationLog;
import com.liupanshui.educenter.entity.vo.LoginLogQuery;
import com.liupanshui.educenter.entity.vo.OperationLogQuery;
import com.liupanshui.educenter.service.LoginLogService;
import com.liupanshui.educenter.service.OperationLogService;
import com.liupanshui.educenter.utils.DateUtils;
import com.liupanshui.educenter.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 登录日志表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-04-12
 */
@RestController
@RequestMapping("/api/log")
@CrossOrigin
public class LogController {

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private OperationLogService operationLogService;
    private final QueryWrapper<LoginLog> wrapper = new QueryWrapper<>();
    // 查询登录日志列表
    @GetMapping("/login/list/{current}/{limit}")
    public R getLoginLogList(@PathVariable Long current, @PathVariable Long limit,
                              LoginLogQuery query) {
        Page<LoginLog> page = new Page<>(current, limit);
        QueryWrapper<LoginLog> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(query.getUsername()), "username", query.getUsername())
                .eq(StringUtils.isNotBlank(query.getIp()), "ip", query.getIp())
                .orderByDesc("login_time");
        IPage<LoginLog> loginLogPage = loginLogService.page(page, wrapper);
        long total = loginLogPage.getTotal();
        List<LoginLog> rows = loginLogPage.getRecords();
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("rows", rows);
        return R.ok().data(data);
    }

    // 导出登录日志
    @GetMapping("/login/export")
    public void exportLoginLog(HttpServletResponse response, LoginLogQuery query) throws IOException {
        List<LoginLog> list = loginLogService.getLoginLogList(query);
        String fileName = "login_log_" + DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN) + ".xlsx";
        ExcelUtils.writeExcel(response, list, LoginLog.class, fileName);
    }

    // 查询操作日志列表
    @GetMapping("/operation/list/{current}/{limit}")
    public R getOperationLogList(@PathVariable Long current, @PathVariable Long limit,
                                 OperationLogQuery query) {
        Page<OperationLog> page = new Page<>(current, limit);
        QueryWrapper<OperationLog> wrapper = new QueryWrapper<>();
//        operationLogService.page(page, new QueryWrapper<OperationLog>()
                wrapper.eq(StringUtils.isNotBlank(query.getOperator()), "operator", query.getOperator())
                .eq(StringUtils.isNotBlank(query.getAction()), "action", query.getAction())
                .orderByDesc("create_time");
        IPage<OperationLog> operationLogIPage = operationLogService.page(page, wrapper);
        long total = operationLogIPage.getTotal();
        List<OperationLog> list = page.getRecords();
        Map<String, Object> data = new HashMap<>();
        data.put("total", total);
        data.put("list", list);
        return R.ok().data(data);
    }

    // 导出操作日志
    @GetMapping("/operation/export")
    public void exportOperationLog(HttpServletResponse response, OperationLogQuery query) throws IOException {
        List<OperationLog> list = operationLogService.getOperationLogList(query);
        String fileName = "operation_log_" + DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN) + ".xlsx";
        ExcelUtils.writeExcel(response, list, OperationLog.class, fileName);
    }



}

