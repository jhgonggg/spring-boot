package com.funtl.hello.spring.boot.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author qy
 * @date 2019/12/23 16:53
 * @description  map
 */
@Component
public class Test {

    //@Scheduled(cron = "0/3 * * * * ?")
    public void test() {
        System.out.println("111111111111");
    }

    public static void main(String[] args) {
        HashMap map2 =new HashMap(2);
        HashMap<String, Object> map = Maps.newHashMap();
        Collection<Object> values = Maps.newHashMap().values();

        map.put("1", 11);
        map.put("2", 22);
        map.put("3", 33);
        map.put("4", 66);

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key);
            System.out.println(value);
        }

        System.out.println("==============================");

        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = set.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
            String key = next.getKey();
            Object value = next.getValue();
        }

        //  .0 表示 保留小数点后面 0 位 ，f 表示传入的参数为 浮点类型 即 123.123
        String format = String.format("%.0f", Double.parseDouble("123.123"));

        System.out.println(Long.parseLong(format));

        long tomorrowTimeStamp = LocalDateTime.of(LocalDate.now().plusDays(NumberUtils.LONG_ONE), LocalTime.MIN)
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
        System.out.println(DateFormatUtils.format(tomorrowTimeStamp, "yyyy-MM-dd HH:mm:ss"));

        int expire = (int) (tomorrowTimeStamp - System.currentTimeMillis()) / 1000;


        List<byte[]> list = Lists.newArrayList();
        byte [] a= new byte[]{98,97};
        list.add(a);
        String s = new String(list.get(0));
        System.out.println(s);

    }

    /**
     * 线程通信
     * 1. Synchronized 同步通信
     * 2. while 轮训方式
     * 3. notify/wait 机制
     * 4. 管道通信机制 PipedOutputStream 、 PipedInputStream
     */
    public static class MyFairLock extends Thread{

        private ReentrantLock lock = new ReentrantLock(true);  // 默认非公平 true 代表公平 false 代表 非公平
        private void fairLock(){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()  + "正在持有锁");
            }finally {
                System.out.println(Thread.currentThread().getName()  + "释放了锁");
                lock.unlock();
            }
        }

        public static void main(String[] args) {
            MyFairLock myFairLock = new MyFairLock();
            Runnable runnable = () -> {
                System.out.println(Thread.currentThread().getName() + "启动");
                myFairLock.fairLock();
            };
            for(int i = 0;i < 10;i++){
                new Thread(runnable).start();
                // 线程启动 通过Runnable
            }
        }
    }

}
