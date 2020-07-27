package com.funtl.hello.spring.boot.test;

import com.funtl.hello.spring.boot.constant.BizConst;
import com.funtl.hello.spring.boot.constant.SysConst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.UriUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qy
 * @date 2020/7/23 10:53
 * @description
 */
public class TestReg {
    /**
     *  匹配
     */
    public static final Pattern HOT_SPOTS_SELECTED_PATTERN = Pattern.compile(".nfapp.southcn.com/apptpl/hotSpotSelection/#/\\?linkId=(\\d+)&articleId=(\\d+)");

    public static void main(String[] args) {

        String text = "%7B%22auditType%22:1,%22auditContent%22:%22\\+12331%22%7D";

        text = StringUtils.replace(text, BizConst.MATCH, BizConst.REPLACE_MATCH);
        text = StringUtils.replace(text, BizConst.ADD, BizConst.REPLACE_2B);
        System.out.println(text);
        text = UriUtils.decode(text, SysConst.UTF_8);

        System.out.println(text);
        ////////////////////////////////////////////////////////////////////////////////

        Matcher matcher = HOT_SPOTS_SELECTED_PATTERN.matcher(".nfapp.southcn.com/apptpl/hotSpotSelection/#/?linkId=123&articleId=467");

        if (matcher.find()){
            String group = matcher.group(1);
            String group1 = matcher.group(2);
            System.out.println(group);
            System.out.println(group1);
        }
        /////////////////////////////////////////////

        boolean matches = matcher.matches();

        System.out.println(matches);
    }
}
