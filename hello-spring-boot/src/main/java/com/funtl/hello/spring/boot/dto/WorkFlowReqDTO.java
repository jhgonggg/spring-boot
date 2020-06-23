package com.funtl.hello.spring.boot.dto;

import com.funtl.hello.spring.boot.annotation.ContentValidate;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Description:提交发起工作流请求dto（适用于pc版、手机版提交工作流的请求表单）
 *
 * @author HUGUANG
 * @date 2019-08-05 下午3:04
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ContentValidate
public class WorkFlowReqDTO {

    @ApiModelProperty(value="当前登录系统的userId")
    @NotBlank
    private String userId;

    @ApiModelProperty(value="工作类型, 1-分享 2-日志 3-采访 4-审批 5-报料 6-签到 7-报题 8-交稿 9-任务 10-编审 12-校对")
    @Range(min = 1, max = 12, message = "参数异常")
    @NotNull
    private Integer type;

    @ApiModelProperty(value="前端无需理会该字段", hidden = true)
    private String videoId;

    @ApiModelProperty(value="点评人、审批人、审稿人")
    private String auditUserId;

    @ApiModelProperty(value="抄送的具体人或者部门，使用\";\"隔开，前面是部门编号，后面是userId，多个用\",\"隔开，如：1407;测试28  表示@的是1407这个部门，以及测试28这个用户,如果没有部门则表示为\";测试28\"")
    private String lookUserId;

    @ApiModelProperty(value="发送范围，具体逻辑需参照pc端, 目前发现只有在app端发送签到才传该字段")
    private String sendRange;

    @ApiModelProperty(value="前端无需理会该字段", hidden = true)
    private String fillTime;

    @ApiModelProperty(value="状态，根据工作类型定,具体逻辑需参照pc端")
    @NotNull
    private Integer status;

    @ApiModelProperty(value="系统类型 1-iPhone 2-Android 3-pc")
    @NotNull(message = "系统类型参数异常")
    @Range(min = 1, max = 3, message = "系统类型参数异常")
    private Integer sysType;

    @ApiModelProperty(value="文章正文@的具体人或者部门, 具体逻辑需参照pc端, 使用\";\"隔开，前面是部门编号，后面是userId，多个用\",\"隔开，如：1407;测试28  标识@的是1407这个部门，以及测试28这个用户,如果没有部门则表示为\";测试28\"")
    private String assignId;

    @ApiModelProperty(value="回执人，多个用\",\"隔开，如果包含部门，则需要把该部门下所有的userId传到后端")
    private String receiptId;

    @ApiModelProperty(value="文章正文插入一个报题， 如\"#苹果手机iOS版中央厨房重装通知#\"")
    private String topic;

    @ApiModelProperty(value="前端无需理会该字段", hidden = true)
    private String fileId;

    @ApiModelProperty(value="签到的坐标，格式采用经纬度，分号隔开，如：22.561016;113.916407")
    private String coordinate;

    @ApiModelProperty(value="地址")
    private String address;

    /**
     * String member = StringUtils.splitDoubleStr((String) params.get("member"));
     */
    @ApiModelProperty(value="发采访的团队成员，发报料提醒人，发报题提醒人，发交稿提醒责编，发采访的团队成员")
    private String member;

    @ApiModelProperty(value="前端无需理会该字段", hidden = true)
    private String updateTime;

    @ApiModelProperty(value="线索（报料）、报题、采访、交稿具有关联关系，通过关联发起的工作才需传改字段，否则不传，具体请参考原有业务")
    private Integer linkFormId;

    @ApiModelProperty(value = "正文@的人或者部门, 格式json数组字符串，请参照原有逻辑，如：[{\"id\":\"技术支持\",\"name\":\"技术支持\",\"type\":1},{\"id\":\"1408\",\"name\":\"产品中心\",\"type\":2},{\"id\":\"yousz\",\"name\":\"yousz\",\"type\":1},{\"id\":\"测试77-部门审核测试77-部门审核\",\"name\":\"测试77-部门审核\",\"type\":1}]")
    private String assignUserId;

    @ApiModelProperty(value="是否私密日志， 0-否， 1-是")
    private Integer isPrivate;

    @ApiModelProperty(value="审校人,只有发交稿、发编审才有该字段")
    private String checkMan;

    @ApiModelProperty(value="部门审稿人,只有发交稿、发编审才有该字段")
    private String leaderMan;

    @ApiModelProperty(value="终审人,只有发交稿、发编审才有该字段")
    private String editorMan;

    @ApiModelProperty(value="前端无需理会该字段", hidden = true)
    private String paramRep;

    @ApiModelProperty(value="版本号,前端无需理会该字段", hidden = true)
    private String version;

    /**
     * 素材,有素材就插入素材表 上传素材集合
     */
    @ApiModelProperty(value="附件ID,上传接口返回的id,多个id逗号相隔")
    private String material;

    /**
     * 上传图片信息
     */
    @ApiModelProperty(value="图片列表,多个mediaId逗号相隔,例：8f8c5a04fb3045a287d3ea62e41fb993.jpg,99271c03b3294f0b8ae0fd969530bf06.jpg")
    private String picList;

    @ApiModelProperty(value="不详,前端暂时无需理会该字段", hidden = true)
    private String oldFile;

    @ApiModelProperty(value="不详,前端暂时无需理会该字段", hidden = true)
    private String buttonId;
    /**
     * 上传视频信息
     */
    @ApiModelProperty(value="视频列表,数组,里面为对象，字段说明请参照 工作流视频上传接口返回字段说明")
    private String videoList;

    /**
     * 有客户就插入客户表
     */
    @ApiModelProperty(value="不详,前端暂时无需理会该字段", hidden = true)
    private String custList;

    /**
     * 日志组id，日志记录
     */
    @ApiModelProperty(value="不详,前端暂时无需理会该字段", hidden = true)
    private String groupId;

    //private String matList;

    /**
     * 内容,js经过JSON.stringify(jsonData); 及 encodeURI
     */
    @ApiModelProperty(value="内容, url编码后的字符串,不同工作流类型不一样，(1.4.0发交稿图文混排功能多增加一个带有html标签的 htmlContent字段，加到content字段，一起提交给我后台即可。发布其他工作类型没有该字段)url编码后的字符串, 如发编审结构：%7b%22grade%22%3a%223%22%2c%22title%22%3a%22%e6%b5%8b%e8%af%9528+%e4%b8%8b%e7%8f%ad%ef%bc%8820%ef%bc%9a14%ef%bc%89%e5%89%8d%e6%9d%a5%e4%b8%80%e4%b8%aa%e5%8f%91%e4%ba%a4%e7%a8%bf%e7%9c%8b%e7%9c%8b%ef%bc%8c%e5%ae%a1%e6%a0%a1%e4%ba%ba%e4%b8%ba%e6%b5%8b%e8%af%9577%e6%88%96%e8%80%85%e6%b5%8b%e8%af%9577-%e9%83%a8%e9%97%a8%e5%ae%a1%e6%a0%b8%ef%bc%8c%e9%83%a8%e9%97%a8%e5%ae%a1%e6%a0%b8%e4%ba%ba%e6%98%af%e6%b5%8b%e8%af%9528%ef%bc%8c%e7%bb%88%e5%ae%a1%e4%ba%ba%e4%b8%ba%e6%b5%8b%e8%af%9577-%e7%bb%88%e5%ae%a1%ef%bc%8c%e8%b4%a3%e7%bc%96%e6%98%af%e7%bb%88%e5%ae%a1%e4%ba%ba%e4%b8%ba%e6%b5%8b%e8%af%9577-%e9%83%a8%e9%97%a8%e5%ae%a1%e6%a0%b8%ef%bc%8c%e5%ae%a1%e7%a8%bf%e6%a8%a1%e5%bc%8f%e6%98%af%e9%87%8d%e8%a6%81%e5%ae%a1%e7%a8%bf%e3%80%82++%22%2c%22visitContent%22%3a%22%e6%b5%8b%e8%af%9528+%e4%b8%8b%e7%8f%ad%ef%bc%8820%ef%bc%9a14%ef%bc%89%e5%89%8d%e6%9d%a5%e4%b8%80%e4%b8%aa%e5%8f%91%e4%ba%a4%e7%a8%bf%e7%9c%8b%e7%9c%8b%ef%bc%8c%e5%ae%a1%e6%a0%a1%e4%ba%ba%e4%b8%ba%e6%b5%8b%e8%af%9577%ef%bc%8c%e9%83%a8%e9%97%a8%e5%ae%a1%e6%a0%b8%e4%ba%ba%e6%98%af%e6%b5%8b%e8%af%9577-%e9%83%a8%e9%97%a8%e5%ae%a1%e6%a0%b8%ef%bc%8c%e7%bb%88%e5%ae%a1%e4%ba%ba%e4%b8%ba%e6%b5%8b%e8%af%9577-%e7%bb%88%e5%ae%a1%ef%bc%8c%e8%b4%a3%e7%bc%96%e6%98%af%e7%bb%88%e5%ae%a1%e4%ba%ba%e4%b8%ba%e9%83%91%e4%bd%b3%e6%ac%a3%ef%bc%8c%e5%ae%a1%e7%a8%bf%e6%a8%a1%e5%bc%8f%e6%98%af%e9%87%8d%e8%a6%81%e5%ae%a1%e7%a8%bf%e3%80%82++%22%2c%22reviewerType%22%3a%221%22%2c%22visitSource%22%3a%223%22%2c%22visitType%22%3a%221%22%2c%22visitPic%22%3a%221%22%2c%22videoFlag%22%3a%221%22%2c%22author%22%3a%22%e6%b5%8b%e8%af%9528%22%2c%22remark%22%3a%22%e5%93%88%e5%93%88%ef%bc%8c%e8%bf%99%e6%98%af%e5%a4%87%e6%b3%a8%e5%93%88%e5%93%88%e5%93%88%22%7d")
    // {"grade":"3","title":"测试28 下班（20：14）前来一个发交稿看看，审校人为测试77或者测试77-部门审核，部门审核人是测试28，终审人为测试77-终审，责编是终审人为测试77-部门审核，审稿模式是重要审稿。  ","visitContent":"测试28 下班（20：14）前来一个发交稿看看，审校人为测试77，部门审核人是测试77-部门审核，终审人为测试77-终审，责编是终审人为郑佳欣，审稿模式是重要审稿。  ","reviewerType":"1","visitSource":"3","visitType":"1","visitPic":"1","videoFlag":"1","author":"测试28","remark":"哈哈，这是备注哈哈哈"}
    @NotBlank
    private String content;

    @ApiModelProperty(value="发交稿上传的组图，json字符串直接传过来即可，不用经过url编码，目前只有发交稿才需要传该字段，其他工作类型不需要传, 如果发交稿没有上传图片，该字段可以不传。字段说明参照图片库即可，都是一致的。")
    private String picAtlas;

    @ApiModelProperty(value="发交稿上传的视频信息，json字符串直接传过来即可，不用经过url编码，目前只有发交稿才需要传该字段，其他工作类型不需要传, 如果发交稿没有上传视频，该字段可以不传。字段说明参照音视频库即可，都是一致的。")
    private String videoAtlas;
}