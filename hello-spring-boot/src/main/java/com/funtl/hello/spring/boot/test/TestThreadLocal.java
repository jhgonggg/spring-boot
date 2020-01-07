package com.funtl.hello.spring.boot.test;

import com.funtl.hello.spring.boot.constant.SysConst;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qy
 * @date 2020/1/7 11:50
 * @description
 */
public class TestThreadLocal {

    public static class ThreadLocalNormalUsage01 {

        public static void main(String[] args)  {
            for (int i = 0; i < 30; i++) {
                int finalI = i;
                new Thread(() -> {
                    String date = new ThreadLocalNormalUsage01().date(finalI);
                    System.out.println(date);
                }).start();
            }
        }

        public String date(int seconds) {

            //参数的单位是毫秒，从1970.1.1 00:00:00 GMT 开始计时
            Date date = new Date(1000 * seconds);
            SimpleDateFormat dateFormat = new SimpleDateFormat(SysConst.DATE_FORMAT);
            return dateFormat.format(date);
        }
    }

    /**
     * 1000个线程打印日期，用线程池来执行
     */
    public static class ThreadLocalNormalUsage02 {

        private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

        public static void main(String[] args) {
            for (int i = 0; i < 1000; i++) {
                int finalI = i;
                //提交任务
                threadPool.submit(() -> {
                    String date = new ThreadLocalNormalUsage02().date(finalI);
                    System.out.println(date);
                });
            }
            threadPool.shutdown();
        }

        public String date(int seconds) {

            //参数的单位是毫秒，从1970.1.1 00:00:00 GMT 开始计时
            Date date = new Date(1000 * seconds);
            SimpleDateFormat dateFormat = new SimpleDateFormat(SysConst.DATE_FORMAT);
            return dateFormat.format(date);
        }
    }


    /**
     * 使用线程池时就会发现每个线程都有一个自己的SimpleDateFormat对象，没有必要，所以将SimpleDateFormat声明为静态，保证只有一个
     * 1000个线程打印日期，用线程池来执行，出现线程安全问题
     */
    public static class ThreadLocalNormalUsage03 {

        private static ExecutorService threadPool = Executors.newFixedThreadPool(10);
        //只创建一次 SimpleDateFormat 对象，避免不必要的资源消耗
        private static SimpleDateFormat dateFormat = new SimpleDateFormat(SysConst.DATE_FORMAT);

        public static void main(String[] args) {
            for (int i = 0; i < 1000; i++) {
                int finalI = i;
                //提交任务
                threadPool.submit(() -> {
                    String date = new ThreadLocalNormalUsage03().date(finalI);
                    System.out.println(date);
                });
            }
            threadPool.shutdown();
        }

        public String date(int seconds) {

            //参数的单位是毫秒，从1970.1.1 00:00:00 GMT 开始计时
            Date date = new Date(1000 * seconds);
            return dateFormat.format(date);
        }
    }


    /**
     * 利用 ThreadLocal 给每个线程分配自己的 dateFormat 对象
     * 不但保证了线程安全，还高效的利用了内存
     */
    public static class ThreadLocalNormalUsage05 {

        private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

        public static void main(String[] args)  {
            for (int i = 0; i < 1000; i++) {
                int finalI = i;
                //提交任务
                threadPool.submit(() -> {
                    String date = new ThreadLocalNormalUsage05().date(finalI);
                    System.out.println(date);
                });
            }
            threadPool.shutdown();
        }

        public String date(int seconds) {

            //参数的单位是毫秒，从1970.1.1 00:00:00 GMT 开始计时
            Date date = new Date(1000 * seconds);
            //获取 SimpleDateFormat 对象
            SimpleDateFormat dateFormat = ThreadSafeFormatter.dateFormatThreadLocal.get();
            return dateFormat.format(date);
        }
    }

    private static class ThreadSafeFormatter {
        // 创建一份 SimpleDateFormat 对象
        private static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat(SysConst.DATE_FORMAT));
    }

}
