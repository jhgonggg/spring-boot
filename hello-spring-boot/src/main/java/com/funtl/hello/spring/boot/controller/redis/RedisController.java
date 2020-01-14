package com.funtl.hello.spring.boot.controller.redis;

import cn.hutool.core.util.StrUtil;
import com.funtl.hello.spring.boot.entity.YbAgree;
import com.funtl.hello.spring.boot.enums.AgreeEnum;
import com.funtl.hello.spring.boot.mapper.YbAgreeMapper;
import com.funtl.hello.spring.boot.redis.RedisManager;
import com.funtl.hello.spring.boot.response.Response;
import com.funtl.hello.spring.boot.response.ResponseBuilder;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
/**
 * 现在我们有一个APP，产品新提出一个叫“程序员树洞”的功能，具体功能就不说了，其中这个功能有一点需要做的是在使用该功能时，如果是首次进入会展示一个协议页面，用户需要勾选后点确定才能进入功能，此后再进该功能，不再显示协议页直接进入该功能。
 * <p>
 * 1、用户点击该功能时前端需要知道该给用户显示哪个页面，这一步需要请求后端接口，后台告诉前端这个用户有没有同意过协议。
 * 2、用户勾选协议点确定，后端需要记录这步操作(记录用户已经同意协议)，这一步需在点确定时前端请求后端接口。
 */

/**
 * @author qy
 * @date 2019/12/27 15:40
 * @description 解决 redis 缓存穿透
 */
@RestController(value = "redis")
public class RedisController {

    @Autowired
    private YbAgreeMapper ybAgreeMapper;

    private final static String AGREE_KEY = "has_agree_key";

    ///////////////////////1. 正常写法 //////////////////////////////////
    @GetMapping(value = "/hasAgree")
    @ApiOperation(value = "判断用户是否已经勾选协议")
    public Mono<Response> hasAgree(@RequestParam(value = "userId", required = true) Integer userId) {
        return Mono.fromCallable(() -> {
            int hasAgree = 0;
            if (RedisManager.sismember(AGREE_KEY, String.valueOf(userId))) {
                hasAgree = 1;
            } else {
                // 查库
                YbAgree agree = new YbAgree();
                agree.setUserId(userId);
                int count = ybAgreeMapper.selectCount(agree);
                if (count > 0) {
                    RedisManager.sadd(AGREE_KEY, String.valueOf(userId));
                    hasAgree = 1;
                }
            }
            return ResponseBuilder.buildSuccess(hasAgree);
        });
    }

    //////////////////////// 2.使用 hash（map） 解决缓存穿透  === 避免多次查库  、这样只会查询一次  /////////////////////////////////////

    private final static String HASH_AGREE_KEY = "hash_has_agree_key";

    @GetMapping(value = "/hasAgree2")
    @ApiOperation(value = "判断用户是否已经勾选协议")
    public Mono<Response> hasAgree2(@RequestParam(value = "userId") Integer userId) {
        return Mono.fromCallable(() -> {
            String hasAgree;
            String value = String.valueOf(RedisManager.hget(HASH_AGREE_KEY, String.valueOf(userId)));
            if (StrUtil.equals(AgreeEnum.YES.getType(), value)) {
                hasAgree = "1";
            } else if (StrUtil.equals(AgreeEnum.NO.getType(), value)) {
                hasAgree = "0";
            } else {
                // 查库
                YbAgree agree = new YbAgree();
                agree.setUserId(userId);
                int count = ybAgreeMapper.selectCount(agree);
                hasAgree = count > 0 ? "1" : "0";
                RedisManager.hset(HASH_AGREE_KEY, String.valueOf(userId), hasAgree);
            }
            return ResponseBuilder.buildSuccess(ImmutableMap.of("hasAgree", hasAgree));
        });
    }


    @PostMapping(value = "saveAgree")
    public Mono<Response> saveAgree(@RequestParam("userId") Integer userId) {
        return Mono.fromCallable(() -> {
            String value = RedisManager.hget(HASH_AGREE_KEY, String.valueOf(userId));
            if (StringUtils.equals(AgreeEnum.YES.getType(), value)) {
                return ResponseBuilder.buildSuccess("已勾选用户协议");
            } else {
                // 数据库勾选
                YbAgree ybAgree = new YbAgree();
                ybAgree.setUserId(userId);
                int insert = ybAgreeMapper.insertSelective(ybAgree);
                RedisManager.hset(HASH_AGREE_KEY, String.valueOf(userId), insert > 0 ? "1" : "0");
                return insert > 0 ? ResponseBuilder.buildSuccess() : ResponseBuilder.buildFail();
            }
        });
    }
}
