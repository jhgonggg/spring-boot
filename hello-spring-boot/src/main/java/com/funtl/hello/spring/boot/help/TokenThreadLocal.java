package com.funtl.hello.spring.boot.help;


import com.funtl.hello.spring.boot.bean.LoginToken;

public class TokenThreadLocal {

    private static final ThreadLocal<LoginToken> THREADLOCAL = new ThreadLocal<>();

    /**
     * 请求前设置当前用户请求的线程副本
     * @param loginToken
     */
    public static void setLoginToken(LoginToken loginToken) {
        THREADLOCAL.set(loginToken);
    }

    /**
     * 从当前用户请求的线程副本中获取当前用户的token对象
     * @return
     */
    public static LoginToken getLoginToken() {
        //注意 ：如果get方法返回值为基本类型，则会报空指针异常，如果是包装类型就不会出错
        return THREADLOCAL.get();
    }

    public static String getUserId() {
        return THREADLOCAL.get().getUserId();
    }

    /**
     * 删除当前用户请求线程的副本
     */
    public static void delLoginToken() {
        THREADLOCAL.remove();
    }
}
