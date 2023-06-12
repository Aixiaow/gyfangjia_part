package com.liupanshui.serviceedu.controller;


import com.liupanshui.commonutils.R;
import com.liupanshui.serviceedu.entity.GuanshanghuData;
import com.liupanshui.serviceedu.service.GuanshanghuDataService;
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
public class GuanshanghuDataController {

    @Autowired
    private GuanshanghuDataService guanshanghuDataService;
    @GetMapping("/getguanshan")
    public R getguanshanAll(){
        List<GuanshanghuData> list = guanshanghuDataService.list(null);
        return R.ok().data("items",list);
    }
}

