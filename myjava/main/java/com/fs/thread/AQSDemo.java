package com.fs.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class AQSDemo {

//    static int counter = 0;
    static volatile int counter = 0; // 不保证原子性，只保证有序性,可见性
//    static AtomicInteger counter = new AtomicInteger();

    static Mutext mutext = new Mutext();



    static class CountDowner implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                mutext.lock();
                counter++;
                mutext.unlock();
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

    static class Mutext extends AbstractQueuedSynchronizer {
        //静态内部类，继承AQS
        private static class Sync extends AbstractQueuedSynchronizer {
            //是否处于占用状态
            protected boolean isHeldExclusively() {
                return getState() == 1;
            }
            //当状态为0的时候获取锁，CAS操作成功，则state状态为1，
            public boolean tryAcquire(int acquires) {
                if (compareAndSetState(0, 1)) { // 这里如果用了sleep方法， 就有问题了
//                    try {
//                        Thread.sleep(1);
//
//                    } catch (Exception e) {
//
//                        e.printStackTrace();
//                    }
                    int start = 0;
                    while (start < Integer.MAX_VALUE) {
                        start++;
                    }
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
                return false;
            }

            public boolean compAndset(int a, int b) {
                return compareAndSetState(a, b);
            }

            //释放锁，将同步状态置为0
            protected boolean tryRelease(int releases) {
                if (getState() == 0) throw new IllegalMonitorStateException();
                setExclusiveOwnerThread(null);
                setState(0);
                return true;
            }
        }
        //同步对象完成一系列复杂的操作，我们仅需指向它即可
        public final Sync sync = new Sync();
        //加锁操作，代理到acquire（模板方法）上就行，acquire会调用我们重写的tryAcquire方法
        public void lock() {
            sync.acquire(1);
        }
        public boolean tryLock() {
            return sync.tryAcquire(1);
        }
        //释放锁，代理到release（模板方法）上就行，release会调用我们重写的tryRelease方法。
        public void unlock() {
            sync.release(1);
        }
        public boolean isLocked() {
            return sync.isHeldExclusively();
        }
    }

}
