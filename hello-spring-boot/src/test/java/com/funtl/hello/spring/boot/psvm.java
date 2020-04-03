package com.funtl.hello.spring.boot;

import cn.hutool.core.collection.IterUtil;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author qy
 * @date 2019/9/11 17:56
 * @description
 */
public class psvm{

    public static void main(String[] args) throws IOException {

        List<Integer> list1 = Lists.newArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(6);

        List<Integer> list2 = Lists.newArrayList();
        list2.add(2);
        list2.add(3);
        list2.add(6);
        AtomicInteger y = new AtomicInteger();
        list1.forEach(integer -> {
            if (IterUtil.isNotEmpty(list2)){
                y.getAndIncrement();
                for (Integer integer1 : list2) {
                    y.getAndIncrement();
                    if (integer.equals(integer1)) {
                        System.out.println(integer1);
                        list2.remove(integer1);
                        break;
                    }
                }
            }

        });


        System.out.println(list2);

        System.out.println(y + "次数");
    }
}
