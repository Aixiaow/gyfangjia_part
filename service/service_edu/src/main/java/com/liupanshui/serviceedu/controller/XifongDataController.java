package com.liupanshui.serviceedu.controller;


import com.liupanshui.commonutils.R;
import com.liupanshui.serviceedu.entity.XifongData;
import com.liupanshui.serviceedu.service.XifongDataService;
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
public class XifongDataController {
    @Autowired
    private XifongDataService xifongDataService;

    @GetMapping("/xifongdata")
    public R xifongAll(){
        List<XifongData> list = xifongDataService.list(null);
        return R.ok().data("items",list);
    }
}

