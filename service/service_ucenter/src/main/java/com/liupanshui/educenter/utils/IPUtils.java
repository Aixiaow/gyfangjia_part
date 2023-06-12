package com.liupanshui.educenter.utils;

import javax.servlet.http.HttpServletRequest;

public class IPUtils {

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        // 获取请求头中的X-Forwarded-For值
        String xForwardedForHeader = request.getHeader("Authorization");
        if (xForwardedForHeader != null && xForwardedForHeader.length() > 0) {
            // X-Forwarded-For可能包含多个IP地址，以逗号分隔
            String[] ips = xForwardedForHeader.split(",");
            // 取最后一个（即客户端真实IP地址）
            ipAddress = ips[ips.length - 1].trim();
        }
        // 如果X-Forwarded-For为空，则尝试获取RemoteAddr
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

}