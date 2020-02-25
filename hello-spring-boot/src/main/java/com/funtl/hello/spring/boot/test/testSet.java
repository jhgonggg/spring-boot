package com.funtl.hello.spring.boot.test;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author qy
 * @date 2020/1/21 09:30
 * @description  FindFirst
 */
public class testSet {

    private static final List<Integer> COLUMN_INDEX;

    static {
        COLUMN_INDEX = new ArrayList<Integer>() {{
            add(3);
            add(6);
            add(7);
        }};
    }

    public static void main(String[] args) {

        String[] s = {"1", "2", "7", "5", "4"};
        List<String> list = Arrays.asList(s);

        List<String> city = Lists.newArrayList();
        city.add("7");
        city.add("0");
        city.add("22");


        Optional<String> first = list.stream().filter(city::contains).findFirst();

        if (first.isPresent()){
            System.out.println(first.get());
        }else {
            System.out.println("不存在");
        }
        Integer type = 7;
        COLUMN_INDEX.remove(type);

        System.out.println(COLUMN_INDEX);

    }
}
