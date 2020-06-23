package com.funtl.hello.spring.boot.strategy2;

import com.funtl.hello.spring.boot.dto.WorkFlowReqDTO;
import com.funtl.hello.spring.boot.entity.YbUser;
import com.funtl.hello.spring.boot.enums.ButtonEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * description 审批通过按钮处理策略  buttonId  --> v_2
 */
@Component
public class ApprovalPass implements WFAuditStrategy {

    @Autowired
    private WFAuditStrategyHelper helper;

    @PostConstruct
    private void init(){
        WFAuditStrategyHelper.auditStrategyMap.put(ButtonEnum.APPROVAL_PASS.getId(),this);
    }

    @Override
    public int execute(YbUser user, WorkFlowReqDTO dto, String auditTime) {
        return 0;
    }

}
