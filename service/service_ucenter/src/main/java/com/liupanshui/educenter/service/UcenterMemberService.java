package com.liupanshui.educenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liupanshui.educenter.entity.UcenterMember;
import com.liupanshui.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    //登录
    String login(UcenterMember member);

    //注册
    void register(RegisterVo registerVo);

    Integer countRegisterDay(String day);

//    List<JSONObject> getMenu(String username);
}
