package com.liupanshui.educenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liupanshui.commonutils.JwtUtils;
import com.liupanshui.commonutils.R;
import com.liupanshui.educenter.entity.LoginLog;
import com.liupanshui.educenter.entity.UcenterMember;
import com.liupanshui.educenter.entity.vo.RegisterQuery;
import com.liupanshui.educenter.entity.vo.RegisterVo;
import com.liupanshui.educenter.mapper.UcenterMemberMapper;
import com.liupanshui.educenter.service.LoginLogService;
import com.liupanshui.educenter.service.OperationLogService;
import com.liupanshui.educenter.service.UcenterMemberService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2023-02-08
 */
@RestController
@RequestMapping("/eduservice/member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;
    @Autowired
    private LoginLogService loginLogService;
    @Autowired
    private OperationLogService operationLogService;

    @Autowired
    private UcenterMemberMapper memberMapper;
    private final QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
    //登录接口
    @PostMapping("/login")
    public R loginUser (@RequestBody UcenterMember member, HttpServletRequest request) {
        //memder 对象佛南郭庄手机号和密码
        //调用service 方法实现登录
        //返回token值，使用jwt生成
        String token = memberService.login(member);
        System.out.println("电话号码"+member);
        // 记录登录日志
        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(member.getMobile());
        loginLog.setLoginTime(new Date());
        loginLog.setIp(getIpAddress(request));
        loginLog.setUserAgent(request.getHeader("Authorization"));
        loginLogService.save(loginLog);
        return R.ok().data("token", token);
    }
    /**
     * 获取请求的IP地址
     */
    private static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isBlank(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        // 如果是多级代理，则取第一个IP地址为客户端IP地址
        int index = ipAddress.indexOf(",");
        if (index != -1) {
            ipAddress = ipAddress.substring(0, index);
        }
        // 如果是本机IP地址，则获取外网IP地址
        if ("127.0.0.1".equals(ipAddress)) {
            try {
                ipAddress = InetAddress.getLocalHost().getHostAddress();
            } catch (java.net.UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return ipAddress;
    }

    /**
     * 判断字符串是否为空或空格
     */
    private static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }



    //根据token获取用户信息
    @GetMapping("/getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        //调用jwt 工具类中的房，根据requset 对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
//        System.out.println("测试"+request);
        System.out.println("测试"+memberId);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);

        System.out.println("测试2"+ member);
        return R.ok().data("userInfo",member);
    }
    // 退出登录
    @PostMapping("/logout")
    public R logout(HttpServletRequest request) {
        JwtUtils.removeToken(request);
        return R.ok();
    }
    //注册
    @PostMapping("/register")
    public R registerUser(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }

    //查询某一天注册人数
    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable String day) {
        Integer count = memberService.countRegisterDay(day);
        return R.ok().data("countRegister",count);
    }
    // 分页查询功能
    @ApiOperation(value = "分页查询功能")
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageTeacher(@PathVariable Long current,
                         @PathVariable Long limit){
        //创建Page 对象
        Page<UcenterMember> pageTeacher = new Page<>(current,limit);
        //调用方法实现分页
        // 调用方法时候，底层封装，把分页所有数据封装到pageTeacher
        memberService.page(pageTeacher,null);
        long teacherTotal = pageTeacher.getTotal();//总记录数
        List<UcenterMember> records = pageTeacher.getRecords();//数据list集合
        Map map = new HashMap();
        map.put("total",teacherTotal);
        map.put("rows",records);
        return R.ok().data(map);
    }

    //条件分页查询用户信息
    @GetMapping("getuser/{current}/{limit}")
    public R getUserPage(@PathVariable Long current,
                     @PathVariable Long limit,
                     @RequestBody(required = false) RegisterQuery registerQuery){
        //创建page对象
        Page<UcenterMember> ucenterMemberPage = new Page<>(current,limit);
        //构建条件
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        Optional<RegisterQuery> optionalQuery = Optional.ofNullable(registerQuery);
        String mobile = optionalQuery.map(RegisterQuery::getMobile).orElse("");
        String nickname = optionalQuery.map(RegisterQuery::getNickname).orElse("");
        Integer sex = optionalQuery.map(RegisterQuery::getSex).orElse(null);
        String begin = optionalQuery.map(RegisterQuery::getBegin).orElse("");
        String end = optionalQuery.map(RegisterQuery::getEnd).orElse("");
//        判断是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(mobile)){
            //构建条件
            wrapper.like("mobile",mobile);
        }
        if (!StringUtils.isEmpty(nickname)){
            wrapper.like("nickname",nickname);
        }
//        if (!StringUtils.isEmpty(sex)){
//            //构建条件
//            wrapper.eq("sex",sex);
//        }
        if (!StringUtils.isEmpty(begin)){
            //构建条件

            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            //构建条件
            wrapper.le("gmt_modified",end);
        }

        //按照时间进行排序
        wrapper.orderByDesc("gmt_create");
        //调用方法实现条件分页查询
        memberService.page(ucenterMemberPage,wrapper);
        long total = ucenterMemberPage.getTotal();//中记录数
        List<UcenterMember> records= ucenterMemberPage.getRecords();//数据list集合
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",records);
        return R.ok().data(map);
    }
    //2 逻辑删除讲师的方法
    @ApiOperation(value = "逻辑删除用户")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "用户ID", required = true)
                           @PathVariable String id) {
        boolean flag = memberService.removeById(id);
        if (flag){
            return R.ok();
        }else {
            return R.error("检索登录日志失败。");
        }
    }
    //获取用户信息
    @GetMapping("{id}")
    public R getUserById(@PathVariable String id){
        UcenterMember ucenterMember = memberMapper.selectById(id);
        return R.ok().data("userdata",ucenterMember);

    }
    //修改用户信息
    @PutMapping("/{id}")
    public R updateUser(@PathVariable String id, @RequestBody UcenterMember user) {
        user.setId(id);
        memberMapper.updateById(user);
        return R.ok();
    }
    // 权限管理接口示例
    @GetMapping("/checkRole")
    public R checkRole(HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        // 查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);

        // 判断用户是否有admin权限
        if ("admin".equals(member.getRoles())) {
            return R.ok().data("role", "admin");
        } else {
            return R.ok().data("role", "user");
        }
    }
    /**
     * 获取菜单
     * @return
     */
//    @GetMapping("menu")
//    public R getMenu(){
//        //获取当前登录用户用户名
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        List<JSONObject> permissionList = memberService.getMenu(username);
//        return R.ok().data("permissionList", permissionList);
//    }
}

