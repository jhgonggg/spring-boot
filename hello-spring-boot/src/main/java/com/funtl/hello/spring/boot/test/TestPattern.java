package com.funtl.hello.spring.boot.test;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @author qy
 * @date 2020/4/23 10:59
 * @description
 */
public class TestPattern {

    public static void main(String[] args) throws IOException {
        Pattern base64Pattern = compile("data:image/([a-zA-Z]{1,3});base64,(.+)");
        String src = "\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAu4AAAIJCAYAAAD6XLjwAAAgAElEQVR4XuydB7RcVfXGf3kvCT30EjoSelFApAuIgKB0AiTSi0r7CwgoglIFBEUEBGmCUqJ0pKMICFIEpIbee4kCCTUhyX+WVeXPnfbPWrJk3uGxACQkAICAEhIASEgBAQAjlAQMQ9B4OkJgoBISAEhIAQEAJCQAgIARF32YAQEAJCQAgIASEgBISAEMgBAiLuORgkNVEICAEhIASEgBAQAkJACIi4ywaEgBAQAkJACAgBISAEhEAOEBBx\"";

        Matcher matcher = base64Pattern.matcher(src);
        if (matcher.find()) {
            // 整个匹配
            String all = matcher.group(0);
            // 得到第一组匹配
            String extension = matcher.group(1);
            // 得到第二组匹配
            String base64 = matcher.group(2);
            // \\s 代表 \ 和 s    \s 代表 一个空白字符（可能是空格、制表符、其他空白）
            String base64String = base64.replaceAll("\\s", "+");
            System.out.println("all --->" + all);
            System.out.println("extension --->" + extension);
            System.out.println("base64 --->" + base64);
            System.out.println("base64String --->" + base64String);
            byte[] bytes = Base64.decodeBase64(base64String);
            try (InputStream inputStream = new ByteArrayInputStream(Base64.decodeBase64(bytes))) {

            }

        }

    }
}
