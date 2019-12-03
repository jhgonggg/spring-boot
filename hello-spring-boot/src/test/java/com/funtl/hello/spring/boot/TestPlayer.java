package com.funtl.hello.spring.boot;

import cn.hutool.core.util.StrUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author qy
 * @date 2019/11/19 15:59
 * @description
 */
public class TestPlayer {

    public static void main(String[] args) throws UnsupportedEncodingException {

        String url = "https://integration-test-v2-1-1256740964.cos.ap-guangzhou.myqcloud.com/test/%E8%AE%BE%E5%A4%87%E9%99%84%E4%BB%B6/%E9%A1%B9%E7%9B%AEid-1322/37ee43ebe14d468e95597e93b05cd0e8/%E5%8F%AA%E8%A6%81%E6%88%91%E6%83%B3.jpg";
        url = URLDecoder.decode(url, "UTF-8");

        System.out.println(url);


        System.out.println(url.substring(url.lastIndexOf("/") + 1));


        System.out.println(StrUtil.subBefore("dskeabcee", "e",true));

        System.out.println(StrUtil.subAfter("dskeabcee", 'e',false));

        System.out.println(StrUtil.sub("dskabcee", 3,4));  // a
    }
}
