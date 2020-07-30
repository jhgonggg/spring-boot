package com.funtl.hello.spring.boot.service.impl;

import com.funtl.hello.spring.boot.base.impl.BaseServiceImpl;
import com.funtl.hello.spring.boot.entity.MonitorConfig;
import com.funtl.hello.spring.boot.service.MonitorConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class MonitorConfigServiceImpl extends BaseServiceImpl<MonitorConfig> implements MonitorConfigService {
}
