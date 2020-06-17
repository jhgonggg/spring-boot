package com.funtl.hello.spring.boot;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Sets;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

/**
 * @author qy
 * @date 2019/11/8 10:46
 * @description
 */
@RunWith(SpringRunner.class)
/* classes = HelloSpringBootApplication.class 为了加载 application.yml 的配置*/
@SpringBootTest(classes = Application.class)
public class Test {

    public static void main(String[] args) throws IOException {
        String[] ss = {"1", "6"};

        String s = append(ss, 3);

        System.out.println(s);

        Set<String> set = Sets.newHashSet();

        set.add("1");
        set.add("2");
        set.add("4");

        String[] array = set.toArray(new String[]{});

        for (String s1 : array) {
            System.out.println(s1);
        }


        String all = StringUtils.replace("111122333", "3", "4");

        System.out.println(all);



    }

    public static String append(Object...arr) {
        StringBuilder sb = new StringBuilder();
        for (Object str : arr) {
            if (str instanceof String[]){
                System.out.println(Arrays.toString((String []) str));
            }
            sb.append(str);
        }
        return sb.toString();
    }

}
