package com.funtl.hello.spring.boot.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @author qy
 * @date 2020/6/18 10:35
 * @description
 */
public class VerifyUtils {

    public static final String IS_MOBILE_REGX = "^(1)\\d{10}$";

    public static final String ID_CARD_REGX = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X|x)$";
    /**
     * 密码强度正则
     */
    public static final String PASSWORD_STRONG_REGX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,22}$";

    /**
     * 验证是否是手机号码接口
     * 如果网络查询请参考 https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php?resource_name=guishudi&query=13225141254
     *
     * @param s
     * @return
     */
    public static boolean isMobilePhone(String s) {
        return StringUtils.isNotEmpty(s) && Pattern.matches(IS_MOBILE_REGX, s);
    }


    /**
     * 检查是否是身份证
     *
     * @param idCard
     * @return
     */
    public static boolean isIdCard(String idCard) {
        return StringUtils.isNotEmpty(idCard) && Pattern.matches(ID_CARD_REGX, idCard);
    }


    /**
     * 验证密码强度
     *
     * @param password
     * @return
     */
    public static boolean vailPasswordStrong(String password) {
        return StringUtils.isNoneEmpty(password) && Pattern.matches(PASSWORD_STRONG_REGX, password);
    }

}
