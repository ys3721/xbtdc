package cn.alip8.juc;

/**
 * @author: Yao Shuai
 * @date: 2021/5/13 0:15
 */
public class VolatileHowImpl {

    private volatile int anInt;

    private int bValue;

    public void add() {
        bValue = 3;
        anInt += 1;
        bValue++;
    }

}
