package com.funtl.hello.spring.boot.dto;

import com.funtl.hello.spring.boot.entity.YbUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author qy
 * @date 2019/10/31 15:06
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoDTO {
    private String username;

    private String password;

    private List<YbUser> ybUser;

}
