package com.funtl.hello.spring.boot.configs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties(prefix = "limit")
@ToString
public class LimitConfig {

    private Long articleClickSentinelOverCount;
    private Integer articleClickSentinelFrequentSecond;
    private Long articleClickSentinelFrequentCount;

}
