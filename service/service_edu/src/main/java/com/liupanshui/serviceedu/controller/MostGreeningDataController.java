package com.liupanshui.serviceedu.controller;


import com.liupanshui.commonutils.R;
import com.liupanshui.serviceedu.entity.MostGreeningData;
import com.liupanshui.serviceedu.service.MostGreeningDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author testjava
 * @since 2023-03-10
 */
@RestController
@RequestMapping("/eduservice")
@CrossOrigin
public class MostGreeningDataController {
    @Autowired
    private MostGreeningDataService mostGreeningDataService;
    @GetMapping("/mostgreen")
    public R getmostGreenAll(){
        List<MostGreeningData> list = mostGreeningDataService.list(null);
        return R.ok().data("items",list);
    }

}

