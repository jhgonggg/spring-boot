package com.funtl.hello.spring.boot.controller;

import cn.hutool.core.collection.IterUtil;
import com.alibaba.fastjson.JSON;
import com.funtl.hello.spring.boot.annotation.NoAuth;
import com.funtl.hello.spring.boot.constant.SysConst;
import com.funtl.hello.spring.boot.dto.MonitorPushDTO;
import com.funtl.hello.spring.boot.entity.MonitorConfig;
import com.funtl.hello.spring.boot.help.TokenThreadLocal;
import com.funtl.hello.spring.boot.response.Response;
import com.funtl.hello.spring.boot.response.ResponseBuilder;
import com.funtl.hello.spring.boot.service.MonitorConfigService;
import com.funtl.hello.spring.boot.service.MonitorSiteService;
import com.funtl.hello.spring.boot.vo.MonitorConfigVO;
import com.funtl.hello.spring.boot.vo.MonitorPushVO;
import com.funtl.hello.spring.boot.vo.MonitorSiteVO;
import com.google.common.base.Splitter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;


@Api(tags = "线索雷达")
@Slf4j
@RestController
@RequestMapping(value = "monitor")
public class MonitorSiteController {

    @Autowired
    private MonitorSiteService monitorSiteService;
    @Autowired
    private MonitorConfigService monitorConfigService;

    @ApiOperation(value = "获取信源站点列表")
    @GetMapping("sites")
    public Mono<Response> sites() {
        return Mono.fromCallable(() -> {
            List<MonitorSiteVO> list = monitorSiteService.sites();
            return ResponseBuilder.buildSuccess(list);
        });
    }

    @ApiOperation(value = "新增或保存监控任务")
    @PostMapping("save")
    public Mono<Response> save(@RequestBody MonitorConfigVO vo) {
        return Mono.fromCallable(() -> {
            monitorSiteService.save(vo);
            return ResponseBuilder.buildSuccess();
        });
    }

    @ApiOperation(value = "删除监控")
    @PostMapping("delete")
    public Mono<Response> delete(@RequestParam(value = "id") int id, @RequestParam(value = "state") boolean status) {
        return Mono.fromCallable(() -> {
            monitorConfigService.updateNotNull(new MonitorConfig().setId(id).setStatus(status).setUpdateTime(new Date()));
            return ResponseBuilder.buildSuccess();
        });
    }

    @ApiOperation(value = "开启/关闭监控")
    @PostMapping("setState")
    public Mono<Response> setState(@RequestParam(value = "id") int id, @RequestParam(value = "state") boolean state) {
        return Mono.fromCallable(() -> {
            monitorConfigService.updateNotNull(new MonitorConfig().setId(id).setState(state).setUpdateTime(new Date()));
            return ResponseBuilder.buildSuccess();
        });
    }

    @ApiOperation(value = "个人监控任务列表")
    @ApiResponses({@ApiResponse(code = 200, message = "个人监控任务列表", response = MonitorConfigVO.class)})
    @GetMapping("list")
    public Mono<Response> list() {
        return Mono.fromCallable(() -> {
            List<MonitorConfigVO> list = monitorSiteService.list(TokenThreadLocal.getUserId());
            return ResponseBuilder.buildSuccess(list);
        });
    }

    @ApiOperation(value = "单个监控任务详情")
    @ApiResponses({@ApiResponse(code = 200, message = "单个监控任务详情", response = MonitorConfigVO.class)})
    @GetMapping("detail")
    public Mono<Response> detail(@RequestParam(value = "id") int id) {
        return Mono.fromCallable(() -> {
            MonitorConfig config = monitorConfigService.selectByKey(id);
            MonitorConfigVO vo = config.convertExt(MonitorConfigVO.class);
            vo.setSites(Splitter.on(SysConst.COMMA).trimResults().splitToList(config.getSites()));
            return ResponseBuilder.buildSuccess(vo);
        });
    }

    /**
     * 网关传过来推送结果
     */
    @PostMapping("receives")
    @NoAuth
    public Mono<Response> receives(String data) {
        List<MonitorPushDTO> list = JSON.parseArray(data, MonitorPushDTO.class);
        log.info("接收到线索雷达数据-->{}", data);
        if (IterUtil.isEmpty(list)) {
            return Mono.fromCallable(ResponseBuilder::buildFail);
        }
        return Mono.fromCallable(() -> {
            monitorSiteService.receives(list);
            return ResponseBuilder.buildSuccess();
        });
    }


    /**
     * 网关传过来信源列表
     */
    @PostMapping("siteList")
    @NoAuth
    public Mono<Response> siteList(String data) {
        log.info("接收到线索雷达信源列表数据-->{}", data);
        if (StringUtils.isBlank(data)) {
            return Mono.fromCallable(ResponseBuilder::buildFail);
        }
        return Mono.fromCallable(() -> {
            monitorSiteService.siteList(data);
            return ResponseBuilder.buildSuccess();
        });
    }

    @ApiOperation(value = "最新50条推送记录")
    @ApiResponses({@ApiResponse(code = 200, message = "最新50条推送记录", response = MonitorPushVO.class)})
    @GetMapping("pushList")
    public Mono<Response> pushList(@RequestParam(value = "id") Integer id) {
        return Mono.fromCallable(() -> ResponseBuilder.buildSuccess(monitorSiteService.pushList(id)));
    }
}