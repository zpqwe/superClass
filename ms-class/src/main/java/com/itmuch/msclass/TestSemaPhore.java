package com.itmuch.msclass;

import java.util.concurrent.Semaphore;

/**
 * @program: ms-class
 * @description:
 * @author: Long Ao Tian
 * @create: 2020-10-29 23:20
 **/
public class TestSemaPhore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println("我抢到了信号量"+ finalI);
                    Thread.sleep(1000L);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }
}

