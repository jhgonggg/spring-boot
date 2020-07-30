package com.funtl.hello.spring.boot.service;

import com.alibaba.fastjson.JSON;
import com.funtl.hello.spring.boot.dto.ArticlePcFlow;
import com.funtl.hello.spring.boot.response.Response;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qy
 * @date 2020/5/8 18:14
 * @description
 */
public class TestService {

    public static void main(String[] args) {
        String result= "{\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"pcFlow\": 1,\n" +
                "            \"articleId\": \"1897956\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pcFlow\": 1,\n" +
                "            \"articleId\": \"1929492\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pcFlow\": 1,\n" +
                "            \"articleId\": \"1929581\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pcFlow\": 1,\n" +
                "            \"articleId\": \"1930351\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pcFlow\": 1,\n" +
                "            \"articleId\": \"1937642\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pcFlow\": 1,\n" +
                "            \"articleId\": \"1938653\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"pcFlow\": 1,\n" +
                "            \"articleId\": \"1939011\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"code\": 200,\n" +
                "    \"msg\": \"成功\",\n" +
                "    \"success\": true\n" +
                "}";
        Response response = JSON.parseObject(result, Response.class);
        List<ArticlePcFlow> articlePcFlows = JSON.parseArray(JSON.toJSONString(response.getData()), ArticlePcFlow.class);
        List<Long> articleIds = articlePcFlows.stream().map(ArticlePcFlow::getArticleId).collect(Collectors.toList());
        String params = articlePcFlows.stream().map(ArticlePcFlow::getArticleId).map(String::valueOf)
                .collect(Collectors.joining(","));
        String params2 = StringUtils.join(articleIds, ",");

        System.out.println(params);

        System.out.println(params2);
    }
}
