//package com.funtl.hello.spring.boot.util;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;
//
//import java.util.MissingResourceException;
//
///**
// * 获取properties中的属性值
// */
//@Component
//public final class PropertiesUtils implements ApplicationContextAware {
//
//    private static volatile ApplicationContext applicationContext;
//
//
////    private static ApplicationContext getApplicationContext() {
////        if (applicationContext == null) {
////            synchronized (PropertiesUtils.class) {
////                if (applicationContext == null) {
////                    applicationContext = ContextLoader.getCurrentWebApplicationContext();
////                }
////            }
////        }
////        return applicationContext;
////    }
//
//    private static Environment env = applicationContext.getBean(Environment.class);
//
//    /**
//     * Get a value based on key , if key does not exist , null is returned
//     *
//     * @param key
//     * @return
//     */
//    public static String getString(String key) {
//        try {
//            return env.getProperty(key);
//        } catch (MissingResourceException e) {
//            return null;
//        }
//    }
//
//    /**
//     * Get a value based on key , if key does not exist , null is returned
//     *
//     * @param key
//     * @return
//     */
//    public static String getString(String key, String defaultValue) {
//        try {
//            String value = env.getProperty(key);
//            if (value == null) {
//                return defaultValue;
//            }
//            return value;
//        } catch (MissingResourceException e) {
//            return defaultValue;
//        }
//    }
//
//    /**
//     * 根据key获取值
//     *
//     * @param key
//     * @return
//     */
//    public static int getInt(String key) {
//        System.out.println(applicationContext);
//        return Integer.parseInt(env.getProperty(key));
//    }
//
//    /**
//     * 根据key获取值
//     *
//     * @param key
//     * @param defaultValue
//     * @return
//     */
//    public static int getInt(String key, int defaultValue) {
//        String value = env.getProperty(key);
//        if (StringUtils.isBlank(value)) {
//            return defaultValue;
//        }
//        return Integer.parseInt(value);
//    }
//
//    /**
//     * 根据key获取值
//     *
//     * @param key
//     * @param defaultValue
//     * @return
//     */
//    public static boolean getBoolean(String key, boolean defaultValue) {
//        String value = env.getProperty(key);
//        if (StringUtils.isBlank(value)) {
//            return defaultValue;
//        }
//        return new Boolean(value);
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        PropertiesUtils.applicationContext=applicationContext;
//    }
//}
