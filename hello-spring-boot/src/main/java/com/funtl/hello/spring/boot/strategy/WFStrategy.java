package com.funtl.hello.spring.boot.strategy;

import com.funtl.hello.spring.boot.dto.WorkFlowReqDTO;
import com.funtl.hello.spring.boot.entity.YbUser;

import java.util.List;

/**
 * 工作流策略
 */
public interface WFStrategy {

    /**
     *  根据每种工作流类型，选择不同的策略，处理不同的业务逻辑，最后返回content信息（每种工作流类型content字段信息都不一致）
     * @param user 当前系统的用户详情信息
     * @param dto 当前保存的工作流表单信息
     * @param formId 已经入库的工作流主键
     */
    void execute(YbUser user, WorkFlowReqDTO dto, int formId);

    /**
     * 用于获取指定的策略处理实现类
     */
    List<Integer> support();
}
