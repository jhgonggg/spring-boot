package com.funtl.hello.spring.boot.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
/**
 * 媒体中心系统用户
 */
public class MediaUserBean implements Serializable {

    private static final long serialVersionUID = 6313873390702816329L;

    /**
     * 用户名
     */
    @ApiModelProperty(value="username用户名")
    private String username;

    /**
     * 署名
     */
    @ApiModelProperty(value="nickname署名")
    private String nickname;

    /**
     * 账号
     */
    @ApiModelProperty(value="accountName账号")
    private String accountName;


    private String id;


}