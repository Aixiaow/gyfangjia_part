package com.liupanshui.serviceedu.controller;


import com.liupanshui.commonutils.R;
import com.liupanshui.serviceedu.entity.XioaheData;
import com.liupanshui.serviceedu.service.XifongDataService;
import com.liupanshui.serviceedu.service.XioaheDataService;
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
public class XioaheDataController {
    @Autowired
    private XioaheDataService xioaheDataService;

    @GetMapping("/xiaohedata")
    public R getxiaoheAll(){
        List<XioaheData> list = xioaheDataService.list(null);
        return R.ok().data("items",list);
    }
}

