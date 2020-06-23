package com.funtl.hello.spring.boot.strategy2;

import com.funtl.hello.spring.boot.dto.WorkFlowReqDTO;
import com.funtl.hello.spring.boot.entity.YbUser;

public interface WFAuditStrategy {

    /**
     * 工作审批
     * @param user 操作用户
     * @param dto 表单信息
     * @param auditTime 审核时间
     */
    int execute(YbUser user, WorkFlowReqDTO dto, String auditTime);

}
