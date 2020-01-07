package com.funtl.hello.spring.boot.test;

import com.funtl.hello.spring.boot.dto.UserDTO;
import com.funtl.hello.spring.boot.entity.YbUser;

import java.util.Optional;

/**
 * @author qy
 * @date 2019/12/26 09:57
 * @description
 */
public class OptionalTest {

    public static void main(String[] args) {

        YbUser user = new YbUser();

        user.setId(999L);

        YbUser u2 = null;

        String s1 = Optional.ofNullable(user).map(YbUser::getUsername).orElse("空");

        System.out.println(s1);

//        String s2 = Optional.of(u2).map(YbUser::getUsername).orElse("空");    // of 与 ofNullable 区别  、of 会报空指针

//////////////////////////////////////////////////////////////////////////////////////////

        UserDTO dto = new UserDTO();

        //1
        String s11 = getScore(dto);
        System.out.println(s11);
        //2
        String s22 = Optional.ofNullable(dto).map(UserDTO::getUser).map(YbUser::getUsername).orElse(null);
        System.out.println(s22);
    }


    private static String getScore(UserDTO dto) {
        if (dto != null) {
            // 第一层 null判空
            YbUser user = dto.getUser();
            // 第二层 null判空
            if (user != null) {
                return user.getUsername();
            }
        }
        return null;
    }


}
