package com.funtl.hello.spring.boot.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.funtl.hello.spring.boot.dto.UserDTO;
import com.funtl.hello.spring.boot.entity.YbUser;
import com.funtl.hello.spring.boot.util.PinyinUtil;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author qy
 * @date 2019/12/26 09:57
 * @description
 */
public class OptionalTest {

    private static final List<YbUser> LIST = Lists.newArrayList();

    static {
        YbUser user = new YbUser();
        user.setId(1L);
        LIST.add(user);
        YbUser user2 = new YbUser();
        user2.setId(2L);
        LIST.add(user2);
    }

    private static final List<Integer> LIST2;

    static {
        LIST2 = new ArrayList<Integer>() {{
            add(1);
            add(2);
        }};
    }

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
        System.out.println("==================================");
        String jsons = "[{\"phone\":\"15678961234\",\"name\":\"百度\",\"userId\":\"test\",\"status\":0}]\n";
        JSONArray array = JSONArray.parseArray(jsons);
        System.out.println(array);  // array ---> jsons 为 "" 时 null 。jsons 为 null 时 null , array 也不会赋值为 new JsonArray()
        // 当 array 为 [] 时 ，不会遍历
        Optional.ofNullable(array).orElseGet(JSONArray::new).forEach(e -> {
            JSONObject json = (JSONObject) e;
            String name = json.getString("name");
            System.out.println(name);
            json.put("initials", PinyinUtil.getPinYinHeadChar(json.getString("name")));
            json.put("pinyin", PinyinUtil.getPingYin(json.getString("name")));
        });

        // 转换成 Map
        Map<Long, YbUser> map = LIST.stream().collect(Collectors.toMap(YbUser::getId, Function.identity(), (k, v) -> v));

        System.out.println(map);
        System.out.println(LIST2);

        List<YbUser> list= null;
        List<YbUser> userList = Optional.ofNullable(list).orElseGet(ArrayList::new).stream().filter(u -> u.getUsername().length() > 6).collect(Collectors.toList());
        System.out.println(userList);

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
