package cn.alip8.juc;

/**
 * 这段代码在 java并发编程中 用于验证cpu的指令重排，
 * 但是为啥不是因为可见性引起的哇，也可能是线程内存可见性的问题哇？？待fixme by yaoshuai
 *
 * @author yaoshuai
 * @date 2021-五月-12
 */
public class DoubleZeroTask {

    private int a = 0;
    private int b = 0;
    private int x, y;

    public void testReorder() throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                a = 1;
                y = b;
            }
        }, "t1");

        Thread tw = new Thread(() -> {
            b = 1;
            x = a;
        }, "t2");

        t1.start();
        tw.start();

        t1.join();
        tw.join();
        System.out.println(x + " " + y);
        if (x == 0 && y == 0) {
            System.exit(-1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000_000_000L; i++) {
            System.out.println(i);
            DoubleZeroTask task  = new DoubleZeroTask();
            task.testReorder();
        }
    }
}
