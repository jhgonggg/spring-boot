package com.funtl.hello.spring.boot.strategy2;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author qy
 * @date 2020/6/23 16:17
 * @description
 */
public class Test {

    @Autowired
    public void test(String buttonId) {
        WFAuditStrategy wfAuditStrategy = WFAuditStrategyHelper.auditStrategyMap.get(buttonId);
        if (wfAuditStrategy != null) {
//           wfAuditStrategy.execute();
        }
    }
}
