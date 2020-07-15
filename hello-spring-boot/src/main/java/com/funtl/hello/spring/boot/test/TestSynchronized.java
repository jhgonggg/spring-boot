package com.funtl.hello.spring.boot.test;

/**
 * @author qy
 * @date 2020/7/15 15:58
 * @description
 */
public class TestSynchronized {


    //静态方法，上类锁，函数功能为连续打印1000个value值，调用时会传1，所以会打印1000个1
    public synchronized static void mB(String value) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + value);
            Thread.sleep(100);
        }
    }

    public void mC(String value) throws InterruptedException {
        //修饰this上对象锁，函数功能也是连续打印1000个value值，调用时会传2，所以会打印1000个2
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + value);
                Thread.sleep(100);
            }
        }
    }

    public static void main(String[] args) {
        TestSynchronized b2 = new TestSynchronized();
        Thread thread = new Thread(() -> {
            try {
                TestSynchronized.mB("1");
//                b2.mC("2");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        TestSynchronized b = new TestSynchronized();
        Thread thread2 = new Thread(() -> {
            try {
                TestSynchronized.mB("1");
//                b.mC("2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread2.start();

    }

}
