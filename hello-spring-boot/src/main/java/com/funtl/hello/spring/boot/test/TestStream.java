package com.funtl.hello.spring.boot.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author qy
 * @date 2020/1/7 10:07
 * @description
 */
public class TestStream {

    public static boolean check(List<Object> list, Map<String, String> map) {
        List<String> result = map.keySet()
                .stream()
                .filter(key -> !list.contains(key))
                .collect(Collectors.toList());
        return result.size() == 0;
    }

    public static void main(String[] args) {
        List<Object> list = Arrays.asList("aaa", "bbb");
        Map<String, String> map = new HashMap<>(3);
        map.put("aaa", "1");
        map.put("bbb", "2");
        //////////////////////1////////////////////////////
        System.out.println(check(list, map));
        ///////////////////// 2 ///////////////////////////
        System.out.println(list.size() == map.size() && list.stream().allMatch(map::containsKey));

        System.out.println(map.keySet().containsAll(list));

    }


}
