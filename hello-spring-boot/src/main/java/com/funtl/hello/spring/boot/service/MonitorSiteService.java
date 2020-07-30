package com.funtl.hello.spring.boot.service;

import com.funtl.hello.spring.boot.base.BaseService;
import com.funtl.hello.spring.boot.dto.MonitorPushDTO;
import com.funtl.hello.spring.boot.entity.MonitorSite;
import com.funtl.hello.spring.boot.vo.MonitorConfigVO;
import com.funtl.hello.spring.boot.vo.MonitorPushVO;
import com.funtl.hello.spring.boot.vo.MonitorSiteVO;

import java.util.List;

public interface MonitorSiteService extends BaseService<MonitorSite> {

    /**
     * 获取所有信源列表
     * @return
     */
    List<MonitorSiteVO> sites();

    /**
     * 保存、更新
     * @param vo 任务监控配置
     */
    void save(MonitorConfigVO vo);

    /**
     * 监控任务列表
     * @param userId 登录用户
     */
    List<MonitorConfigVO> list(String userId);

    /**
     * 接收监控数据
     */
    void receives(List<MonitorPushDTO> list);

    /**
     * 获取个人图送列表（最新50条）
     */
    List<MonitorPushVO> pushList(Integer id);

    /**
     * 信源列表数据
     */
    void siteList(String data);
}
