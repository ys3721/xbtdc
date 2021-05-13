package cn.alip8.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author yaoshuai
 * @date 2021-五月-13
 */
public class Solver {
    final int N;
    final int[][] data;
    final CyclicBarrier barrier;



    public Solver(int[][] matrix) {
        data = matrix;
        N = matrix.length;
        Runnable barrierAction =
                new Runnable() {
                    public void run() {
                        mergeRows();
                    }
                };
        barrier = new CyclicBarrier(N, barrierAction);

        List<Thread> threads = new ArrayList<Thread>(N);
        for (int i = 0; i < N; i++) {
            Thread thread = new Thread(new Worker(i));
            threads.add(thread);
            thread.start();
        }

        // wait until done
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void mergeRows() {
        System.out.println("Merge rows!!!!!!!!!!!!!!!!!!");
    }

    class Worker implements Runnable {
        int myRow;

        Worker(int row) {
            myRow = row;
        }

        public void run() {
                processRow(myRow);
                try {
                    barrier.await();
                } catch (InterruptedException ex) {
                    return;
                } catch (BrokenBarrierException ex) {
                    return;
                }
        }

        private void processRow(int myRow) {
            try {
                System.out.println(Thread.currentThread().getName() +" begin process myRow!!");
                Thread.sleep((long) (Math.random() * 5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}