package com.liupanshui.serviceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liupanshui.commonutils.R;
import com.liupanshui.serviceedu.entity.AllData;
import com.liupanshui.serviceedu.entity.vo.AllDataQuery;
import com.liupanshui.serviceedu.service.AllDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-03-11
 */
@RestController
@RequestMapping("/eduservice/alldata")
@CrossOrigin
public class AllDataController {

    @Autowired
    AllDataService allDataService;

    //基本查询实现
    @GetMapping("/getAllData")
    public R getAllData(){
        List<AllData> allData = allDataService.list(null);
        return R.ok().data("list",allData);
    }
    //分页查询功能
    @GetMapping("pageAllDataa/{course}/{limit}")
    public R getPageCourse(@PathVariable long course,@PathVariable long limit){
        Page<AllData> allDataPage = new Page<>(course, limit);
        allDataService.page(allDataPage,null);
        long total = allDataPage.getTotal();//总数记录
        List<AllData> records = allDataPage.getRecords();//数据list集合
        Map map = new HashMap();
        map.put("total",total);
        map.put("records",records);
        return R.ok().data(map);
    }
    //分页查询带分页功能
    @PostMapping("pageAllData/{current}/{limit}")
    public R pageCourseCondition(@PathVariable long current, @PathVariable long limit,
                                 @RequestBody(required = false) AllDataQuery allDataQuery){
        //创建page对象
        Page<AllData> allDataPage = new Page<>(current,limit);
        //构建条件
        QueryWrapper<AllData> wrapper = new QueryWrapper<>();
        String district = allDataQuery.getDistrict();//行政区
        String idtyId = allDataQuery.getIdtyId();//地址
        Integer mumber_kindergartens = allDataQuery.getMumber_kindergartens();//附近幼儿园数
        //判断是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(district)){
            //构建条件
            wrapper.like("district",district);
        }if (!StringUtils.isEmpty(idtyId)){
            wrapper.like("idty_id",idtyId);
        }if (!StringUtils.isEmpty(mumber_kindergartens)){
            wrapper.eq("mumber_kindergartens",mumber_kindergartens);
        }
        //按照幼儿园多少进行排序
        wrapper.orderByDesc("mumber_kindergartens");
        //调用方法实现条件分页查询
        allDataService.page(allDataPage,wrapper);
        long total = allDataPage.getTotal();//中记录数
        List<AllData> records= allDataPage.getRecords();//数据list集合
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",records);
        return R.ok().data(map);
    }


}

