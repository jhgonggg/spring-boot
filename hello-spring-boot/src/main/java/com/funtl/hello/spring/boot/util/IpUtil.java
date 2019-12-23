package com.funtl.hello.spring.boot.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author qy
 * @date 2019/12/23 10:12
 * @description
 */
@Slf4j
public class IpUtil {

    private static final String UNKNOWN = "unknown";
    private static final String LOCALHOST_IP = "127.0.0.1";
    private static final String LOCALHOST_IP_16 = "0:0:0:0:0:0:0:1";
    private static final int MAX_IP_LENGTH = 15;
    private static final String COMMA = ",";

    /**
     * 获得用户远程地址
     *
     * @param request the request
     * @return the string
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Real-IP");
        if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("X-Forwarded-For");
        }
        if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("PROXY_CLIENT_IP");
        }
        if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL_PROXY_CLIENT_IP");
        }
        if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        if (StringUtils.isEmpty(ipAddress) || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (LOCALHOST_IP.equals(ipAddress) || LOCALHOST_IP_16.equals(ipAddress)) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error("获取IP地址, 出现异常={}", e.getMessage(), e);
                }
                assert inet != null;
                ipAddress = inet.getHostAddress();
            }
            log.info("获取IP地址 ipAddress={}", ipAddress);
        }
        // 对于通过多个代理的情况, 第一个IP为客户端真实IP,多个IP按照','分割 //"***.***.***.***".length() = 15
        if (ipAddress != null && ipAddress.length() > MAX_IP_LENGTH) {
            if (ipAddress.indexOf(COMMA) > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(COMMA));
            }
        }
        return ipAddress;
    }
}
