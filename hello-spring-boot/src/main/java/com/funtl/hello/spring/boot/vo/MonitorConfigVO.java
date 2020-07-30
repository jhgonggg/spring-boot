package com.funtl.hello.spring.boot.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonitorConfigVO implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value="id主键")
    private Integer id;

    /**
     * 用户id
     */
    @ApiModelProperty(value="userId用户id", hidden = true)
    private String userId;

    /**
     * 监控主题名
     */
    @ApiModelProperty(value="name监控主题名")
    @NotBlank
    private String name;

    /**
     * 是否开启监控
     */
    @ApiModelProperty(value="status是否开启监控")
    private Boolean state;

    /**
     * 推送条数
     */
    @ApiModelProperty(value="pushNum推送条数",hidden = true)
    private Integer pushNum;

    /**
     * 包含关键词（如果所有关键字则用 .*表示）
     */
    @ApiModelProperty(value="keywordSearch包含关键词（如果所有关键字则用 .*表示）")
    @NotBlank
    private String keywordSearch;

    /**
     * 同时包含关键字
     */
    @ApiModelProperty(value="keywordMust同时包含关键字")
    private String keywordMust;

    /**
     * 排除关键字
     */
    @ApiModelProperty(value="keywordExclude排除关键字")
    private String keywordExclude;

    /**
     * 站点信源
     */
    @ApiModelProperty(value="sites站点信源")
    @NotEmpty
    private List<String> sites;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public MonitorConfigVO setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public MonitorConfigVO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 获取监控主题名
     *
     * @return name - 监控主题名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置监控主题名
     *
     * @param name 监控主题名
     */
    public MonitorConfigVO setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 获取是否开启监控
     *
     * @return status - 是否开启监控
     */
    public Boolean getState() {
        return state;
    }

    /**
     * 设置是否开启监控
     *
     * @param state 是否开启监控
     */
    public MonitorConfigVO setState(Boolean state) {
        this.state = state;
        return this;
    }

    /**
     * 获取推送条数
     *
     * @return push_num - 推送条数
     */
    public Integer getPushNum() {
        return pushNum;
    }

    /**
     * 设置推送条数
     *
     * @param pushNum 推送条数
     */
    public MonitorConfigVO setPushNum(Integer pushNum) {
        this.pushNum = pushNum;
        return this;
    }

    /**
     * 获取包含关键词（如果所有关键字则用 .*表示）
     *
     * @return keyword_search - 包含关键词（如果所有关键字则用 .*表示）
     */
    public String getKeywordSearch() {
        return keywordSearch;
    }

    /**
     * 设置包含关键词（如果所有关键字则用 .*表示）
     *
     * @param keywordSearch 包含关键词（如果所有关键字则用 .*表示）
     */
    public MonitorConfigVO setKeywordSearch(String keywordSearch) {
        this.keywordSearch = keywordSearch;
        return this;
    }

    /**
     * 获取同时包含关键字
     *
     * @return keyword_must - 同时包含关键字
     */
    public String getKeywordMust() {
        return keywordMust;
    }

    /**
     * 设置同时包含关键字
     *
     * @param keywordMust 同时包含关键字
     */
    public MonitorConfigVO setKeywordMust(String keywordMust) {
        this.keywordMust = keywordMust;
        return this;
    }

    /**
     * 获取排除关键字
     *
     * @return keyword_exclude - 排除关键字
     */
    public String getKeywordExclude() {
        return keywordExclude;
    }

    /**
     * 设置排除关键字
     *
     * @param keywordExclude 排除关键字
     */
    public MonitorConfigVO setKeywordExclude(String keywordExclude) {
        this.keywordExclude = keywordExclude;
        return this;
    }
}