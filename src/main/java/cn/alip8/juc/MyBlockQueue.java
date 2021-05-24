package cn.alip8.juc;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yaoshuai
 * @date 2021-五月-24
 */
public class MyBlockQueue<T> {

    private ArrayList<T> baseList = new ArrayList<>();

    private volatile int maxValue = 10;

    public synchronized void put(T value) throws InterruptedException {
        if (baseList.size() >= maxValue) {
            this.wait();
        }
        baseList.add(value);
        this.notify();
    }

    public synchronized T take() throws InterruptedException {
        if (baseList.isEmpty()) {
            this.wait();
        }
        T t = baseList.remove(0);
        this.notify();
        return t;
    }

    public static void productAndCost() {
        MyBlockQueue<Integer> myBlockQueue = new MyBlockQueue<>();
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 1000; i++) {
            es.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        myBlockQueue.put((int) (Math.random() * 100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        ExecutorService es2 = Executors.newWorkStealingPool(20);
        es2.submit(new Runnable() {
            @Override
            public void run() {
                Integer tv = null;
                try {
                    tv = myBlockQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(tv);
            }
        });
    }

    public static void main(String[] args) {
        productAndCost();
    }
}
