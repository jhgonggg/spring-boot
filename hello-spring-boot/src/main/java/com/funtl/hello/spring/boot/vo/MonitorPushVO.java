package com.funtl.hello.spring.boot.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.funtl.hello.spring.boot.constant.SysConst;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 接收南都的线索雷达数据（字段、格式需要跟他们那边保持一致）
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonitorPushVO implements Serializable {

    private Integer id;

    @ApiModelProperty(value = "接收消息的用户id")
    private String userId;

    @ApiModelProperty(value = "抓取标题")
    private String title;

    @ApiModelProperty(value = "对应信源名称")
    private String mediaName;

    @ApiModelProperty(value = "链接")
    private String url;

    @ApiModelProperty(value = "来自平台")
    private String platform;

    @ApiModelProperty(value = "发送时间（推送时间）")
    @JsonFormat(pattern = SysConst.DATE_FORMAT)
    private Date sendTime;

    @ApiModelProperty(value = "稿件发布时间")
    @JsonFormat(pattern = SysConst.DATE_FORMAT)
    private Date pushTime;


    @ApiModelProperty(value = "摘要")
    private String summary;

    @ApiModelProperty(value = "pv")
    private Integer pvCount;

    @ApiModelProperty(value = "对应信源编码")
    private String mediaId;

    @ApiModelProperty(value = "回复数")
    private Integer replyCount;
}