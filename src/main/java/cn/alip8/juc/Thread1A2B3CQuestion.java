package cn.alip8.juc;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
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
}
