package com.funtl.hello.spring.boot.help;

import com.funtl.hello.spring.boot.bean.MediaUserBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 用户信息 threadLocal
 */
@Slf4j
public class SessionLocalThread {

    private SessionLocalThread(){}

    private static ThreadLocal<MediaUserBean> threadLocal = new ThreadLocal<>();

    public static void set(MediaUserBean bean){
        threadLocal.set(bean);
    }

    public static MediaUserBean getCurrentUser(){
        return threadLocal.get();
    }

    public static String getCurrentUserId(){
        if(Objects.nonNull(threadLocal.get())){
            return threadLocal.get().getUsername();
        }
        return StringUtils.EMPTY;
    }

    public static void removeLocalThread() {
        if (Objects.nonNull(threadLocal)) {
            threadLocal.remove();
            log.info("删除ThreadLocal中的用户成功-->{}", getCurrentUserId());
        }
    }
}
