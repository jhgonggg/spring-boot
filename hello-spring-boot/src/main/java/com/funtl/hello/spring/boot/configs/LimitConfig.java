package com.funtl.hello.spring.boot.configs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter
@Setter
@ConfigurationProperties(prefix = "limit")
@ToString
@Component
public class LimitConfig {

    private Long articleClickSentinelOverCount;
    private Integer articleClickSentinelFrequentSecond;
    private Long articleClickSentinelFrequentCount;

}
