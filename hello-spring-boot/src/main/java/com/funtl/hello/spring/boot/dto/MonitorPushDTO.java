package com.funtl.hello.spring.boot.dto;

import com.funtl.hello.spring.boot.constant.SysConst;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 接收南都的线索雷达数据（字段、格式需要跟他们那边保持一致）
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonitorPushDTO implements Serializable {

    /**
     * 系统唯一id
     */
    private String doc_id;

    /**
     * 抓取标题
     */
    @Builder.Default
    private String title = StringUtils.EMPTY;

    /**
     * 抓取ip
     */
    private String send_ip;

    /**
     * 抓取时间
     */
    @DateTimeFormat(pattern = SysConst.DATE_FORMAT)
    private Date send_time;

    /**
     * 对应信源名称
     */
    private String media_name;

    /**
     * 链接
     */
    @Builder.Default
    private String url = StringUtils.EMPTY;

    /**
     * 来自平台
     */
    private String platform;

    /**
     * 摘要
     */
    private String summary;

    /**
     * pv
     */
    private Integer pv_count;

    /**
     * 对应信源编码
     */
    private String media_id;

    /**
     * 回复数
     */
    private Integer reply_count;


    /**
     * 抓取内容
     */
    @ApiModelProperty(value="content抓取内容")
    private String content;

    @DateTimeFormat(pattern = SysConst.DATE_FORMAT)
    private Date ptime;
}