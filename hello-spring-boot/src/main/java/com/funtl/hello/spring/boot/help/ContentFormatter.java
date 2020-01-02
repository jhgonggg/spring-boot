package com.funtl.hello.spring.boot.help;

import com.funtl.hello.spring.boot.constant.BizConst;
import com.funtl.hello.spring.boot.constant.SysConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;
import org.springframework.web.util.UriUtils;

import java.text.ParseException;
import java.util.Locale;

/**
 * Description: 参数绑定，自动解码前端经过url编码的字符串
 *
 */

@Slf4j
public class ContentFormatter implements Formatter<String> {
    @Override
    public String parse(String text, Locale locale) throws ParseException {
        if (StringUtils.isBlank(text)) {
            return text;
        }
        text = StringUtils.replace(text, BizConst.MATCH, BizConst.REPLACE_MATCH);
        text = StringUtils.replace(text, BizConst.ADD, BizConst.REPLACE_2B);
        text = UriUtils.decode(text, SysConst.UTF_8);
        return text;
    }

    @Override
    public String print(String object, Locale locale) {
        return object;
    }
}
