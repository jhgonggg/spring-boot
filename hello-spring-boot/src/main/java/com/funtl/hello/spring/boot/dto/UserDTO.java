package com.funtl.hello.spring.boot.dto;

import com.alibaba.fastjson.annotation.JSONField;
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

    private String USER_ID;

    private String descMap;

    /**
     * 属性大小写问题 首字母
     * @return
     */
    @JSONField(name = "USER_ID")
    public String getUSER_ID() {
        return USER_ID;
    }
}
