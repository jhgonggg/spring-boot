package com.funtl.hello.spring.boot.strategy;

import com.funtl.hello.spring.boot.dto.WorkFlowReqDTO;
import com.funtl.hello.spring.boot.entity.YbUser;
import com.funtl.hello.spring.boot.enums.WorkFlowTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Description: 审批工作内容处理策略, 返回url解码后的工作内容
 *
 */

@Slf4j
@Component
public class ApprovalStrategy implements WFStrategy {


    @Override
    public void execute(YbUser user, WorkFlowReqDTO dto, int formId) {
        // do something
    }

    @Override
    public List<Integer> support() {
        return Collections.singletonList(WorkFlowTypeEnum.APPROVAL.getType());
    }
}
