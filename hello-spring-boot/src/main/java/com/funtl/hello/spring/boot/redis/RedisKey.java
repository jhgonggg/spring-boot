package com.funtl.hello.spring.boot.redis;


public class RedisKey {
    /**
     * pc用户登录的key
     */
    public static final String USER_LOGIN_KEY = "user.login.key.";
	/**
	 * pc用户登录的 session Key
	 */
    public static final String USER_SESSION_KEY = "user.session.key.";

    /**
     * 用于删除key 替代keys
     */
    public static final String APP_CLEAR_KEYS_SET = "app.clear.keys.set";
    /**
     * 每日key清理标志
     */
    public static final String CLEAR_KEY_SET_FLAG = "clear.key.set.flag";

}
