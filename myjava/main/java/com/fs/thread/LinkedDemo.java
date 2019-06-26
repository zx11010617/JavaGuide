package com.fs.thread;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedDemo {

    public static void main(String[] args) throws Exception {
        LinkedBlockingQueue<String> q = new LinkedBlockingQueue<>(10);
        for (int i = 0; i < 11; i++) {
            System.out.println(q.offer("000"));
        }
    }
}
