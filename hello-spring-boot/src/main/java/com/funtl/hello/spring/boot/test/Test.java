package com.funtl.hello.spring.boot.test;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.*;

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

    }


}
