package com.funtl.hello.spring.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qy
 * @date 2019/10/25 10:17
 * @description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String info;

    private String username;

    private String password;

    private String message;

    private String date;

}
