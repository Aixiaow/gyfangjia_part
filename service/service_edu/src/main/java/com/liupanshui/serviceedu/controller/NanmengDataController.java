package com.liupanshui.serviceedu.controller;


import com.liupanshui.commonutils.R;
import com.liupanshui.serviceedu.entity.NanmengData;
import com.liupanshui.serviceedu.service.NanmengDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
 * @since 2023-03-08
 */
@RestController
@RequestMapping("/eduservice")
@CrossOrigin
public class NanmengDataController {
    @Lazy
    @Autowired
    private NanmengDataService nanmengService;

    @GetMapping("/nanmeng")
    public R nanmengmap(){
        List<NanmengData> maplist = nanmengService.list(null);
        return R.ok().data("items",maplist);
    }
}

