package com.funtl.hello.spring.boot.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qy
 * @date 2019/10/25 10:11
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO<T> {

    private T content;
}
