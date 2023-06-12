package com.liupanshui.educenter.config;

import com.liupanshui.commonutils.JwtUtils;
import com.liupanshui.educenter.service.impl.UcenterMemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private UcenterMemberServiceImpl memberService;

    private JwtUtils jwtUtils;

    public JwtFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 验证 Token 是否有效
        String jwtToken = request.getHeader("liu_token");//Authorization
        System.out.println("这个不对吗"+jwtToken);
        if (JwtUtils.checkToken(jwtToken)) {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);

//            UcenterMember member = memberService.getById(memberId);
//            Authentication authentication = new UsernamePasswordAuthenticationToken(
//                    member, null, member.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 继续执行 Filter 链
        filterChain.doFilter(request, response);
    }




}
