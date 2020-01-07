package com.funtl.hello.spring.boot.test;

/**
 * @author qy
 * @date 2020/1/7 15:09
 * @description 演示 ThreadLocal 的用法2：避免参数传递的麻烦
 * 达到线程安全的目的
 * 不需要加锁，执行效率高
 * 更加节省内存，节省开销
 * 免去传参的繁琐，降低代码耦合度
 */
public class TestThreadLocal2 {

    public static void main(String[] args) {
        new Service1().process();
    }
}

class Service1 {

    public void process() {
        User user = new User("鲁毅");
        //将User对象存储到 holder 中
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}

class Service2 {

    public void process() {
        User user = UserContextHolder.holder.get();
        System.out.println("Service2拿到用户名: " + user.name);
//        UserContextHolder.remove();  每次使用完需要调用 防止内存泄漏
        new Service3().process();
    }
}

class Service3 {

    public void process() {
        User user = UserContextHolder.holder.get();

        System.out.println("Service3拿到用户名: " + user.name);
    }
}


class UserContextHolder {

    public static ThreadLocal<User> holder = new ThreadLocal<>();

    public static void remove(){
        holder.remove();
    }

}

class User {

    String name;

    User(String name) {
        this.name = name;
    }
}

