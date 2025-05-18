package com.itheima;

public class ThreadLocalTest {
    public static ThreadLocal<String> local = new ThreadLocal<>();

    public static void main(String[] args) {
        local.set("Main msg");
        new Thread(new Runnable() {
            @Override
            public void run() {
                local.set("sub msg");
                System.out.println(Thread.currentThread().getName() + ": " +local.get());
            }
        }).start();
        System.out.println(Thread.currentThread().getName() + ": " +local.get());
        local.remove();
        System.out.println(Thread.currentThread().getName() + ": " +local.get());
    }
}
