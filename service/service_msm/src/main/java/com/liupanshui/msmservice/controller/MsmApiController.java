package com.liupanshui.msmservice.controller;

import com.liupanshui.commonutils.R;
import com.liupanshui.msmservice.service.Msmservice;
import com.liupanshui.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 短信模块
 */
@RestController
@RequestMapping("/msm")
@CrossOrigin
public class MsmApiController {

    @Autowired
    private Msmservice msmservice;

    @Autowired
//    使用redis 设置验证时间
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone){
        //1、从redis 获取验证吗，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return R.ok();
        }
        //2 如果 redis 获取不到，进行阿里云发送
        // 生成随机值，传递阿里云进行发送

        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        //调用service发送短信的方法
        boolean isSend = msmservice.send(param,phone);
        if (isSend){
            //发送成功，吧发送成功的验证码放到redis中
            //设置有效时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        }else {
            return R.error("检索登录日志失败。").message("发送短信失败");
        }
    }
}
