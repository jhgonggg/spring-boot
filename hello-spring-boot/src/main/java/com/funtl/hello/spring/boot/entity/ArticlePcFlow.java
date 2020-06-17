package com.funtl.hello.spring.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qy
 * @date 2020/3/25 09:50
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticlePcFlow {

    /**
     * 稿件 id
     */
    private Long articleId;

    /**
     * 点击数
     */
    private int pcFlow;

    /**
     * 点击数加权
     */
    private int pcWeightFlow;

}
