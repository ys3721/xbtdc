package cn.alip8.juc;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yaoshuai
 * @date 2021-五月-13
 */
public class CasAndAqsTask {

    Lock lock;

    public void seekAQS() {
        AbstractQueuedSynchronizer abstractQueuedSynchronizer = new AbstractQueuedSynchronizer() {
        };
        lock = new ReentrantLock();
        lock.lock();
    }
}
