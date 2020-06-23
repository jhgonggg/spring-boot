package com.funtl.hello.spring.boot.strategy2;

import com.funtl.hello.spring.boot.dto.WorkFlowReqDTO;
import com.funtl.hello.spring.boot.entity.YbUser;
import com.funtl.hello.spring.boot.enums.ButtonEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author yousz
 * @date 2019/9/10
 * description
 */
@Component
@Slf4j
public class ManuscriptAgree implements WFAuditStrategy {


    /**
     * 也是同意签发的意思
     */
    @PostConstruct
    private void init(){
        WFAuditStrategyHelper.auditStrategyMap.put(ButtonEnum.MANUSCRIPT_AGREESEND.getId(),this);
    }


    @Override
    public int execute(YbUser user, WorkFlowReqDTO dto, String auditTime) {
        return 0;
    }
}
