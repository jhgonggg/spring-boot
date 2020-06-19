package com.funtl.hello.spring.boot.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        /////////////////////////////////////////////////
        map.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });

        ////////////////////////////////////////////////////
        Stream.of("one", "two", "three", "four").forEach(x -> {
            System.out.println(x + "A");
        });
        System.out.println("=============");

        // peek 没有返回值，只适合对内部属性的操作 ，收集元素本身。  ---> [one, two, three, four] 可以看出还是收集的元素本身

        List<String> collect2 = Stream.of("one", "two", "three", "four").peek(x -> x = x + 1).collect(Collectors.toList());

        // map有返回值，收集返回后的元素  ---> [one1, two1, three1, four1]
        List<String> collect = Stream.of("one", "two", "three", "four").map(x -> x + 1).collect(Collectors.toList());

        System.out.println(collect);
        ///////////////////////////////////////////////
        System.out.println("#####################");

        String[] words = {"Hello", "World"};

        List<String> collect1 = Stream.of(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());

        System.out.println(collect1);
    }


}
