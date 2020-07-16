package com.funtl.hello.spring.boot.redis;


public class RedisKey {

    /**
     * 稿件点击统计限流key
     */
    public static final String ARTICLE_CLICK_SENTINEL_OVER_PREFIX = "article.click.sentinel.over.prefix.";
    public static final String ARTICLE_CLICK_SENTINEL_FREQUENT_PREFIX = "article.click.sentinel.frequent.prefix.";

    /**
     * 编审抄送用户工作队列
     */
    public static final String REVIEWER_PERSON_LIST = "reviewer.person.list";

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

    public static final String REDIS_COLUMN_CACHE_CHANNEL = "redis.column.cache.channel";

}
