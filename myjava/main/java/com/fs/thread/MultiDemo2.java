package com.fs.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MultiDemo2 {

//    static int counter = 0;
//    static volatile int counter = 0; // 不保证原子性，只保证有序性,可见性
    static AtomicInteger counter = new AtomicInteger();



    static class CountDowner implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
//                counter++;
                counter.getAndIncrement();
            }
        }
    }


    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 10; i++) {
            new Thread(new CountDowner()).start();
        }

        Thread.sleep(1000);
        System.out.println(counter);
        System.exit(0);


    }

}
