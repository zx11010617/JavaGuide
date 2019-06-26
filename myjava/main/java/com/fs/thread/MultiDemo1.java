package com.fs.thread;


import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class MultiDemo1 {
    ExecutorService pool = Executors.newCachedThreadPool();




    public static void main(String[] args) throws Exception {

//        log.info((1 << (Integer.MAX_VALUE - 3)) + "");
        new MultiDemo1().start2();
       // new MultiDemo1().futureTaskTest();
    }


    public void futureTaskTest() throws Exception {
        FutureTask<String> task = new FutureTask<String>(new Runnable() {
            @Override
            public void run() {
                log.info("job");
                try {

                    Thread.sleep(2000);

                } catch (Exception e) {

                    e.printStackTrace();
                }
                //throw  new RuntimeException("finish ");
            }
        }, "dddd");
        // FutureTask -> RunnableFuture
        // AbstractExecutorService.submit() -> AbstractExecutorService.execute(FutureTask) -> ThreadPoolExecutor.execute(FutureTask)
        // RunnableFuture -> Callable属性

        // 添加queue
        // queue取出来执行

        // RunnableFuture.run( {callable.call();  set | setException} ) //callable.call调用的是Runnable.run(),返回了result "dddd"
        // Runnable.run() ->  -> Callable.call() ->
        // pool.sumit(runnable) -> Runnable -> FutureTask -> ThreadPoolExecutor.execute()


        // FutureTask2的run()给多线程调的，里面会解阻塞
        pool.submit(task);
        String s = task.get(); // 会阻塞调用线程（main）,直到任务线程执行结束，
        // FutureTask的run() 异步调用， （task的call 方法， call内部调用传入的Runnable的run()方法)， FutureTask的run里面会调set或setException方法(会调finishcompletion方法，会调LockSupport.unpark方法)，从而去除阻塞
        log.info(s + " ====== ");
    }

    public void parkTest() throws Exception {

        Object lock = new Object();
        log.info("start work ... ");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("inner job ...");
                LockSupport.park(lock); // 阻塞
                log.info("finish inner job ...");

                LockSupport.park(lock);
                log.info("inner job2 ...");
                log.info("finish inner job2 ...");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("t2 inner job ...");
                LockSupport.park(lock); // 阻塞
                log.info("t2 finish inner job ...");

                try {
                    Thread.sleep(3000);

                } catch (Exception e) {

                    e.printStackTrace();
                }
                LockSupport.park(lock);
//                LockSupport.park(lock);
                log.info("t2 inner job2 ...");
                log.info("t2 finish inner job2 ...");
            }
        });
        thread.start();
        thread2.start();

        int ct = 0;
        while (ct++ < 2) {
            log.info("job work ... ");
        }
        log.info("finish job ... ");
        Thread.sleep(1000);
        LockSupport.unpark(thread); // 释放阻塞
        LockSupport.unpark(thread2); // 释放阻塞

//        Thread.sleep(2000);
//        Thread.sleep(2000);
        log.info("start release 2 ... ");
//        LockSupport.unpark(thread);
        LockSupport.unpark(thread2);

    }

    public void start2() throws Exception {

        ExecutorService pool = Executors.newFixedThreadPool(1);
        Callable<String> task1 = () ->  {
            log.info("enter task1 ...");
            Thread.currentThread().sleep(2000);

            return "1";
        };
        Callable<String> task2 = () ->  {
            log.info("enter task2 ...");
            Thread.currentThread().sleep(1000);
            log.info("enter fi ...");
            return "2";
        };
        Callable<String> task3 = () ->  {
            log.info("enter task3 ...");
            Thread.currentThread().sleep(3000);

            return "3";
        };
        List<Future<String>> re = pool.invokeAll(Arrays.asList(task1, task2, task3)); // 会阻塞在这里，直到所有的都执行完。
        List<Future<String>> re2 = pool.invokeAll(Arrays.asList(task1, task2, task3), 1, TimeUnit.SECONDS); // 给了时间的话，会在总时间到的时候，如果没有执行完会cancel,用future也取不到

        String s1 = re.get(0).get(); //
        log.info(s1);
//        String s2 = re2.get(0).get(); // 会报错，因为cancel的原因

        String s = pool.invokeAny(Arrays.asList(task1, task2, task3)); // 完成了1个就退出了，别的不会执行

        log.info("finish ..." + s);
        pool.shutdown();
    }


    public void start() throws Exception {

        log.info("start ...");
        Future<String> future = pool.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                int ct = 0;
                while (ct++ < 2) {
                    Thread.sleep(1000);
                    log.info("execute call ...");
                }
                if (ct > 0) {
                    throw new RuntimeException("error");
                }
                return null;
            }
        });
        log.info("before get ...");

//        if (future.get() == null) {
//
//        } // 会被这个方法阻塞
        log.info(future.isDone() + "");
        Thread.sleep(6000);
        log.info(future.isDone() + ""); // 抛了异常也是执行完了，返回true
        log.info("after get ...");


        Thread.yield(); // 对操作系统的线程调度器建议作线程切换
    }


}
