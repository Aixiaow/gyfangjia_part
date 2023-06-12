package com.liupanshui.serviceedu.controller;


import com.liupanshui.commonutils.R;
import com.liupanshui.serviceedu.entity.YunyanData;
import com.liupanshui.serviceedu.service.YunyanDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-03-09
 */
@RestController
@RequestMapping("/eduservice")
@CrossOrigin
public class YunyanDataController {
    @Autowired
    private YunyanDataService yunyanDataService;
    @GetMapping("/yunyandata")
    public R getyunyanAll(){
        List<YunyanData> list = yunyanDataService.list(null);
        return R.ok().data("items",list);
    }
}

