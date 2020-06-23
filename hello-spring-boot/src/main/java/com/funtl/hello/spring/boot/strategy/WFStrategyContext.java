package com.funtl.hello.spring.boot.strategy;

import com.funtl.hello.spring.boot.constant.SysConst;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Description:
 *
 * @author HUGUANG
 * @date 2019/9/2 14:18
 */
@Component
@Slf4j
public class WFStrategyContext implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * 根据业务类型来拆分工作类型
     */
    private Map<Integer, WFStrategy> strategyMap = Maps.newHashMap();

    public WFStrategy chooseStrategy(Integer workFlowType) {
        Assert.notNull(workFlowType, "workFlowType must not null...");
        Assert.isTrue(SysConst.WORK_TYPES.contains(workFlowType), "workFlowType must in WorkFlowTypeEnum type...");
        return strategyMap.get(workFlowType);
    }

    @PostConstruct
    public void registerWfTypeClass() {
        Map<String, WFStrategy> wFStrategyMap = applicationContext.getBeansOfType(WFStrategy.class);
        if (MapUtils.isEmpty(wFStrategyMap)) {
            log.error("******未获取到工作流类型服务实现类******");
            return;
        }
        log.info("bean容器中获取到工作流类型服务实现类-->{}", wFStrategyMap.size());
        wFStrategyMap.values().forEach(wfStrategy -> wfStrategy.support().forEach(e -> {
            strategyMap.put(e, wfStrategy);
            log.info("put WFStrategy class to map success-->{}, support-->{}, map size-->{}", wfStrategy.getClass().getName(), wfStrategy.support(), strategyMap.size());
        }));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
