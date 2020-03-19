package com.funtl.hello.spring.boot.controller;

import com.funtl.hello.spring.boot.response.Response;
import com.funtl.hello.spring.boot.response.ResponseBuilder;
import com.funtl.hello.spring.boot.util.EnumsUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(value = "/api/draw")
@Slf4j
@Api(value = "/api/draw", tags = "下拉列表管理")
public class DrawController {

    @GetMapping("/list/{enumName}")
    @ApiOperation(value = "枚举类下拉列表 ,通过传入枚举类的类名来返回枚举集合 如: RoleEnum")
    public Mono<Response> listByEnumName(@PathVariable(name = "enumName") @ApiParam(value = "枚举类名称") String enumName) {
        return Mono.fromCallable(() -> ResponseBuilder.buildSuccess(EnumsUtils.convert(enumName)));
    }
}
