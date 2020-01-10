package com.funtl.hello.spring.boot.test;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

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

    }

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
            }
        }
    }

}
