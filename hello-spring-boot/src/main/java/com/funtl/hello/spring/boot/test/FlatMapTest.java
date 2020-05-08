package com.funtl.hello.spring.boot.test;

import com.funtl.hello.spring.boot.constant.SysConst;
import com.funtl.hello.spring.boot.util.CommonHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qy
 * @date 2020/4/23 16:16
 * @description
 */
public class FlatMapTest {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Java 8", "Lambdas", "I n", "Action");
        List<String[]> collect = words.stream()
                .map(w -> w.split("")).collect(Collectors.toList());

        collect.forEach(c-> {
            System.out.println(Arrays.toString(c));;
        });

        List<String> uniqueCharacters =
                words.stream()
                        .map(w -> w.split(StringUtils.EMPTY))
                        // 扁平化收集
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(Collectors.toList());
        System.out.println(uniqueCharacters);


        float v = NumberUtils.toFloat("3", 7f);
        System.out.println(v);


        String a= "你";

        String b = "我,你";

        List<String> aa = CommonHelper.getListBySplitter(a, SysConst.COMMA);
        List<String> bb = CommonHelper.getListBySplitter(b, SysConst.COMMA);
        System.out.println(aa);
        System.out.println(bb);
        System.out.println("---------------------------");


        boolean flag = aa.containsAll(bb);

        System.out.println(flag);

    }
}
