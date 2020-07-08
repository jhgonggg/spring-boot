package com.funtl.hello.spring.boot.test;

import com.funtl.hello.spring.boot.entity.YbUser;

import java.util.*;
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


        List<YbUser> lists = new ArrayList<>();
        lists.add(YbUser.builder().id(11L).username("bbb").build());
        lists.add(YbUser.builder().id(10L).username("ccc").build());

        List<YbUser> list2 = new ArrayList<>();
        lists.add(YbUser.builder().id(11L).username("aaa").build());
        lists.add(YbUser.builder().id(13L).username("bbb").build());

        // 根据id去重并排序
        // Collectors.toCollection 把收集的元素放到创建的指定集合，如 下面放到 treeSet 并且id排序的集合
        // Collectors.collectingAndThen 对结果做相应的转换 ，如 下面 将收集的 treeSet 转换成 ArrayList
        List<YbUser> unique = lists.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(YbUser::getId).reversed())), ArrayList::new));
        System.out.println(unique);


        ArrayList<Long> collect3 = Stream.of(list2, lists).flatMap(Collection::stream).map(YbUser::getId).collect(Collectors.collectingAndThen(
                Collectors.toCollection(TreeSet::new), ArrayList::new));

        System.out.println(collect3);


         // 分类成一个key为true和false的Map
        List<String> list5 = Arrays.asList("java", "python", "C++","php","java");
        Map<Boolean, List<String>> result = list5.stream().collect(Collectors.partitioningBy(s -> s.length() > 3));
        System.out.println(result);

        TreeSet<YbUser> collect4 = lists.stream().collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(YbUser::getId).reversed())));


    }


}
