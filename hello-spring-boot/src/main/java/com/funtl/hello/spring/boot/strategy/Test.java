package com.funtl.hello.spring.boot.strategy;

import com.funtl.hello.spring.boot.enums.WorkFlowTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author qy
 * @date 2020/6/23 16:01
 * @description
 */
public class Test {

    @Autowired
    private WFStrategyContext strategyContext;

    public void test(){
        WFStrategy wfStrategy = strategyContext.chooseStrategy(WorkFlowTypeEnum.APPROVAL.getType());
//        wfStrategy.execute();
    }
}
