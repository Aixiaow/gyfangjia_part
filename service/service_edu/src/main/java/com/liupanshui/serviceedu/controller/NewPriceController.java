package com.liupanshui.serviceedu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liupanshui.commonutils.R;
import com.liupanshui.serviceedu.entity.NewPrice;
import com.liupanshui.serviceedu.entity.vo.NewPriceQuery;
import com.liupanshui.serviceedu.service.NewPriceService;
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
 * @since 2023-03-19
 */
@RestController
@RequestMapping("/eduservice")
@CrossOrigin
public class NewPriceController {
    @Autowired
    private NewPriceService newPriceService;

    @GetMapping("/newprice")
    public R newPrice(){
        List<NewPrice> list = newPriceService.list(null);
        return R.ok().data("items",list);
    }

    // 条件分页查询带分页功能
    @PostMapping("/newprice/pageAllData/{current}/{limit}")
    public R pageCourseCondition(@PathVariable long current,@PathVariable long limit,
                                 @RequestBody(required = false) NewPriceQuery newPriceQuery){
        //创建page对象
        Page<NewPrice> page = new Page<>(current, limit);
        //构建条件
        QueryWrapper<NewPrice> wrapper = new QueryWrapper<>();
        String district = newPriceQuery.getDistrict();//行政区
        String village = newPriceQuery.getVillage();//商业圈
        //判断是否为空，如果为空不拼接条件
        if (!StringUtils.isEmpty(district)){
            //构建条件
            wrapper.like("district",district);
        }if (!StringUtils.isEmpty(village)){
            wrapper.like("village",village);
        }
        //调用方法实现分页查询
        newPriceService.page(page,wrapper);
        long total = page.getTotal();//总记录数
        List<NewPrice> records = page.getRecords();
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",records);
        return R.ok().data(map);

    }



}

