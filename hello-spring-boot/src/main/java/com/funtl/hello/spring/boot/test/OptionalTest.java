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
        dto.setMessage("1111");
        dto.setUser(new YbUser());
        //1
        String s11 = getScore(dto);
        System.out.println(s11);
        //2
        String s22 = Optional.ofNullable(dto).map(UserDTO::getUser).map(YbUser::getUsername).orElse(null);
        System.out.println(s22);

        // 没有 orElse 或者 orElseGet 返回的是 Optional 对象 、 有的话返回的是实体对象
        System.out.println(Optional.ofNullable(dto).map(UserDTO::getUser));
        // Optional[YbUser{id=null, username='null', password='null', email='null', gender=null, birth=null, picture='null', created=null, location='null', phone='null', updated=null, isOnline='null', isRole=null}]
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
