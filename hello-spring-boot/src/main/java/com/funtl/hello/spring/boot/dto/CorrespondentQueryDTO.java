package com.funtl.hello.spring.boot.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author qy
 * @date 2019/9/26 17:08
 * @description 通讯员查询请求实体
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CorrespondentQueryDTO {

    @ApiModelProperty(value = "添加人")
    private String userId;

    @ApiModelProperty(value = "每页的显示数目")
    @Range(min = 1, max = 1000)
    @NotNull
    private Integer pageSize;

    @ApiModelProperty(value = "页码 从 1 开始，1-第一页")
    @Range(min = 1, max = 2147483647)
    @NotNull
    private Integer pageNumber;

    @ApiModelProperty(value = "搜索框字段")
    private String search;

    @ApiModelProperty(value = "类型，1-新闻秘书  2-通讯员")
    private Integer type;

    @ApiModelProperty(value = "地区，21个地市+其他，共22个选项")
    private String area;

}
