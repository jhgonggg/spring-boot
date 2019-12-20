package com.funtl.hello.spring.boot.strategy;

import com.funtl.hello.spring.boot.service.Operate;
import lombok.Data;

/**
 * @author qy
 * @date 2019/12/20 17:37
 * @description
 */
@Data
public class RoleContext {
    // 可更换的策略，传入不同的策略对象，业务即相应变化
    private Operate operation;

    public RoleContext(Operate operation ) {
        this.operation = operation;
    }

    public String execute(String role) {
        return operation.op(role);
    }
}
