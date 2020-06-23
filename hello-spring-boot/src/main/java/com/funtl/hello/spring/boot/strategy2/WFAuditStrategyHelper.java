package com.funtl.hello.spring.boot.strategy2;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;



@Component
@Slf4j
public class WFAuditStrategyHelper {

    public static Map<String, WFAuditStrategy> auditStrategyMap = Maps.newHashMap();

    // 此类还可写通用的方法

}
