package com.funtl.hello.spring.boot.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonitorSiteVO implements Serializable {

    /**
     * 所属组编码
     */
    @ApiModelProperty(value="groupid所属组编码")
    private String groupId;

    /**
     * 所属组（类别）
     */
    @ApiModelProperty(value="groupName所属组（类别）")
    private String groupName;


    /**
     * 站点类型
     */
    @ApiModelProperty(value="type站点类型")
    private String type;

    /**
     * 子类别
     */
    private List<SiteVO> sites;
}
