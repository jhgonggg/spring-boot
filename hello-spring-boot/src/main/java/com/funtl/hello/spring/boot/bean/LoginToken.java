package com.funtl.hello.spring.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginToken {

    private Long timestamp;

    private String userId;

    private String uuid;

    private String deviceId;
}
