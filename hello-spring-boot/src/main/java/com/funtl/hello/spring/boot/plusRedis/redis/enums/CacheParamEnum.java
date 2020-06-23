package com.funtl.hello.spring.boot.plusRedis.redis.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * <p>
 * 注解介绍
 * cacheable 获取缓存 如果有缓存 直接返回
 * <p>
 * value	String[]	缓存的名称 和cacheNames功能一样
 * cacheNames	String[]	缓存的名称和value功能一样
 * key	String	缓存key的值、默认是以所有的参数作为key、也可以直接配置keyGenerator
 * keyGenerator	String	缓存key的生成器
 * cacheManager	String	配置使用那个缓存管理器、和cacheResolver排斥
 * cacheResolver	String	定义使用那个拦截器、和cacheManager互斥
 * condition	String	根据spel表达式来可以配置什么条件下进行缓存 默认全部缓存
 * unless	String	和condition相反
 * sync	boolean	是否开启同步功能、默认不开启
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum CacheParamEnum {

    //  eq  @Cacheable(value = "userCache", key = "'user-info::'.concat(#userId)", unless = "#result eq null")
    // @GetCache(prefix = "userVisitAuditList", key = {"#userId", "#type", "#visitType"}, expire = 300)
    /**
     * 获取用户审核人缓存key参数
     */
    USER_VISITAUDIT_LIST("user.visit.audit.list.", 3600L),
    /**
     * 所有用户 userId缓存key参数
     */
    USER_CACHE("all.userId.", 300L),

    USER("user.",100L),

    ;

    /**
     * redis的key前缀
     */
    private String prefix;

    /**
     * 过期时间
     */
    private Long expire;
}
