package com.liupanshui.serviceedu.controller;


import com.liupanshui.commonutils.R;
import com.liupanshui.serviceedu.entity.MostPopularData;
import com.liupanshui.serviceedu.service.MostPopularDataService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2023-03-10
 */
@RestController
@RequestMapping("/eduservice/most-popular-data")
public class MostPopularDataController {
    @Autowired
    private MostPopularDataService mostPopularDataService;
    @GetMapping("mostPopular")
    public R getMostPopularAll(){
        List<MostPopularData> list = mostPopularDataService.list(null);
        return R.ok().data("items",list);
    }

}

