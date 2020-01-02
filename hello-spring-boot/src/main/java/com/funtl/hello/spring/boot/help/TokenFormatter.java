package com.funtl.hello.spring.boot.help;

import com.funtl.hello.spring.boot.bean.LoginToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

/**
 * Description: 从TokenThreadLocal获取用户id,强制覆盖前端传过来的userId
 *
 *  * <p>
 *  *     dto接收参数类接口在这里做越权处理，考虑到前期项目绝大部分接口使用 userId 作为当前系统登录id，
 *  *     为了不用对原有接口都修改一遍，否则工作量过大。如果后续新接口 用户id 不以 userId 命名，只需要在
 *  *     controller层接口从TokenThreadLocal获取用户id即可，不用显示依赖springmvc执行参数绑定
 *  *</>
 *  *
 *  * <p>
 *  *     从TokenThreadLocal获取用户id,强制覆盖前端传过来的userId
 *  * </p>
 */

@Slf4j
public class TokenFormatter implements Formatter<String> {
    @Override
    public String parse(String text, Locale locale) throws ParseException {
        LoginToken loginToken = TokenThreadLocal.getLoginToken();
        return Objects.nonNull(loginToken) ? loginToken.getUserId() : text;
    }

    @Override
    public String print(String object, Locale locale) {
        return object;
    }
}
