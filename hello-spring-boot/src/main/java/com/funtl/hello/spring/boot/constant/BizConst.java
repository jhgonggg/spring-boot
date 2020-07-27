package com.funtl.hello.spring.boot.constant;


public final class BizConst {

    /**
     * 前端提交表单对字段进行url解码用到  不是 0-9、a-f 十六进制的 2位
     * 查找后面不是[0-9a-fA-F]{2}的% 用于将这个 % 转换成 %2B
     */
    public static final String MATCH = "%(?![0-9a-fA-F]{2})";
    /**
     * 前端提交表单对字段进行url解码用到
     */
    public static final String REPLACE_MATCH = "%25";  // 解码以后 %
    /**
     * 前端提交表单对字段进行url解码用到
     */
    public static final String ADD = "\\+";
    /**
     * 前端提交表单对字段进行url解码用到
     */
    public static final String REPLACE_2B = "%2B"; // 解码以后 +

}
