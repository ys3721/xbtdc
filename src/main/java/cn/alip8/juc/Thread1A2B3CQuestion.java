package cn.alip8.juc;

import java.lang.Thread.State;
import java.lang.reflect.Field;
import java.util.concurrent.locks.LockSupport;

/**
 * @author yaoshuai
 * @date 2021-四月-29
 */
public class Thread1A2B3CQuestion {

    char[] nums = "1234567890".toCharArray();

    char[] alpha = "abcdefghij".toCharArray();


    public void singleThreadImpl(int eachThreadCount) {
        int testLenth = eachThreadCount * 2;
        int p1 = 0 , p2 = 0;
        for (int i = 0; i < testLenth; i++) {
            if (i % 2 == 0) {
                System.out.print(nums[p1++ % 10]);
            } else {
                System.out.print(alpha[p2++ % 10]);
            }
        }
    }

    public void alternateExecute(int eachThreadCount) throws Exception{
        Thread t1 = new Thread("t1");
        Thread t2 = new Thread("t2");

        PackPrintRunner t1Runer = new PackPrintRunner(t2, nums, eachThreadCount);
        PackPrintRunner t2Runer = new PackPrintRunner(t1, alpha, eachThreadCount);;

        Field runnableField = Thread.class.getDeclaredField("target");
        runnableField.setAccessible(true);
        runnableField.set(t1, t1Runer);
        runnableField.set(t2, t2Runer);


        t1.start();
        t2.start();
        LockSupport.unpark(t1);
        //为了测试代码结束
        t1.join();
        t2.join();
    }

    public void waitNotifyExecute(int printLength) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < printLength; i++) {
                    //use nums as a lock
                    System.out.print(nums[i % 10]);
                    synchronized (nums) {
                        nums.notify();
                    }
                    try {
                        synchronized (alpha) {
                            alpha.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
                for(int i = 0; i < printLength; i++) {
                    try {
                        synchronized(nums) {
                            nums.wait();
                            synchronized (alpha) {
                                System.out.print(alpha[i % 10]);
                                alpha.notify();
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }, "waitNotifyT2");

        t2.start();
        //Only t2 is wait the lock of nums, then t1 begin start.
        while (t2.getState() != State.WAITING) {
            //loop
        }
        t1.start();
        //Test case need the t1 and t2 finish
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class PackPrintRunner implements Runnable{

        private int index = 0;

        private final Thread otherThread;

        private final char[] contendChars;

        private final int count;

        PackPrintRunner(Thread otherThread, char[] contendChars, int count) {
            this.otherThread = otherThread;
            this.contendChars = contendChars;
            this.count = count;
        }

        @Override
        public void run() {
            for(int i = 0; i < count; i++) {
                LockSupport.park();
                System.out.print(contendChars[index++ % 10]);
                LockSupport.unpark(otherThread);
            }

        }
    }

    public static void main(String[] args) {
        Thread1A2B3CQuestion a2B3CQuestion = new Thread1A2B3CQuestion();
        a2B3CQuestion.waitNotifyExecute(1000);
    }
}
