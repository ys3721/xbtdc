package cn.alip8.algorithm;

import cn.alip8.algorithm.slidewindow.CordCoverMaxPoint;
import cn.alip8.util.AlgorithmTestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author: Yao Shuai
 * @date: 2021/4/13 10:34
 */
public class CordCoverMaxPointTest {

    @Test
    public void testCordCoverMaxPoint() {
        int len = 20000;
        int max = 10000000;
        int testCount = 200;
        for (int i = 0; i < testCount; i++) {
            int l = (int) (Math.random() * max);
            int[] arr = AlgorithmTestUtils.genLargeThenZeroNoRepeat(len, max);
            int ans0 = CordCoverMaxPoint.maxPoint0(arr, l);
            int ans1 = CordCoverMaxPoint.maxPoint1(arr, l);
            int ans2 = CordCoverMaxPoint.maxPoint2(arr, l);
            int ans3 = CordCoverMaxPoint.maxPoint3(arr, l);
            Assert.assertTrue("能够覆盖的点是" + ans1,
                    ans0 == ans1
                            && ans1 == ans2 );
        }
    }

    private int[] ls;
    private int[][] targets;

    @Before
    public void praper() {
        int count = 50;
        int len = 20000;
        int max = 1000000;
        ls = new int[count];
        targets = new int[count][];
        for (int i = 0; i < count; i++) {
            ls[i] = (int) (Math.random() * max);
            targets[i] = AlgorithmTestUtils.genLargeThenZeroNoRepeat(len, max);
        }
    }

    @Test
    public void testOne() {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < ls.length; i++) {
            CordCoverMaxPoint.maxPoint0(targets[i], ls[i]);
        }
        System.out.println("Brutal Cost time = " + (System.currentTimeMillis() - begin));
    }

    @Test
    public void testTwo() {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < ls.length; i++) {
            CordCoverMaxPoint.maxPoint1(targets[i], ls[i]);
        }
        System.out.println("Binary Cost time = " + (System.currentTimeMillis() - begin));
    }

    @Test
    public void testThree() {
        long begin = System.currentTimeMillis();
        for (int i = 0; i < ls.length; i++) {
            CordCoverMaxPoint.maxPoint2(targets[i], ls[i]);
        }
        System.out.println("Slider window Cost time = " + (System.currentTimeMillis() - begin));
    }
}
