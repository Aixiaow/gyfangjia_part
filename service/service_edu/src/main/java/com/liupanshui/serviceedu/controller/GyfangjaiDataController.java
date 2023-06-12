package com.liupanshui.serviceedu.controller;

import com.alibaba.fastjson.JSON;
import com.liupanshui.commonutils.R;
import com.liupanshui.serviceedu.entity.GyfangjaiData;
import com.liupanshui.serviceedu.service.GyfangjaiDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
@RequestMapping("/eduservice")
@CrossOrigin
@CacheConfig(cacheNames = "gydata")
public class GyfangjaiDataController {

    @Autowired
    private GyfangjaiDataService gyfangjaiDataService;

    @GetMapping("/getAll")
    public R getGydataAll() {
        Jedis jedis = new Jedis("192.168.133.130"); // 创建 Redis 客户端对象
        String cacheKey = "gydataAll";
        String cachedValue = jedis.get(cacheKey); // 从缓存中获取数据
        if (cachedValue != null) {
            // 如果缓存中有数据，则反序列化缓存的数据并返回
            List<GyfangjaiData> list = JSON.parseArray(cachedValue, GyfangjaiData.class);
            return R.ok().data("items", list);
        } else {
            // 如果缓存中没有数据，则查询数据库，并将查询结果缓存到 Redis 中
            List<GyfangjaiData> list = gyfangjaiDataService.list(null);
            String serializedValue = JSON.toJSONString(list); // 序列化为 JSON 字符串
            jedis.set(cacheKey, serializedValue);
            jedis.expire(cacheKey, 300); // 设置过期时间为 300 秒
            return R.ok().data("items", list);
        }
    }

}
