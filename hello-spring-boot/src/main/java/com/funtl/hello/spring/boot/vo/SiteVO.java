package com.funtl.hello.spring.boot.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SiteVO implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value="code")
    private String code;
    /**
     * 站点
     */
    @ApiModelProperty(value="name站点")
    private String name;

    /**
     * 状态
     */
    @ApiModelProperty(value="state状态")
    private Integer state;
}
