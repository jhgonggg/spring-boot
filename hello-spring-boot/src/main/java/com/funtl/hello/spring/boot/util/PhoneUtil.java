package com.funtl.hello.spring.boot.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author qy
 * @date 2019/12/11 14:38
 * @description
 */
public class PhoneUtil {

    public static boolean isMobileNumber(final String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return false;
        }
        final String reg = "^((\\+?86)|(\\(\\+86\\)))?(13[0-9][0-9]{8}|14[0-9]{9}|15[0-9][0-9]{8}|16[0-9][0-9]{8}|17[0-9][0-9]{8}|18[0-9][0-9]{8})$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }
}
