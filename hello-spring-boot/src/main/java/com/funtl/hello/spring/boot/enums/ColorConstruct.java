package com.funtl.hello.spring.boot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author qy
 * @date 2019/12/20 17:18
 * @description
 */

@AllArgsConstructor
@Getter
public enum ColorConstruct {

    RED(1), GREEN(2), BLANK(3), YELLO(4);

    private int index;


    public static void main(String[] args) {
        System.out.println( ColorConstruct.values().length);  // 4
        System.out.println( ColorConstruct.RED.toString());  // RED
        System.out.println(ColorConstruct.valueOf("RED"));   // RED
        System.out.println( ColorConstruct.valueOf("RED") == ColorConstruct.RED);
    }
}

