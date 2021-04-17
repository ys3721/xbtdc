package cn.alip8.algorithm;

import cn.alip8.algorithm.slidewindow.CordCoverMaxPoint;
import cn.alip8.util.AlgorithmTestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: Yao Shuai
 * @date: 2021/4/13 10:34
 */
public class CordCoverMaxPointTest {

    @Test
    public void testCordCoverMaxPoint() {
        int len = 10000;
        int max = 1000;
        int testCount = 100;
        for (int i = 0; i < testCount; i++) {
            int l = (int) (Math.random() * max);
            int[] arr = AlgorithmTestUtils.generateSortedArray(len, max, false);
            int ans0 = CordCoverMaxPoint.maxPoint0(arr, l);
            int ans1 = CordCoverMaxPoint.maxPoint1(arr, l);
            int ans2 = CordCoverMaxPoint.maxPoint2(arr, l);
            int ans3 = CordCoverMaxPoint.maxPoint3(arr, l);
            System.out.println(ans0);
            System.out.println(ans1);
            //Assert.assertTrue("能够覆盖的点是" + ans1,ans0 == ans1 && ans1 == ans2 && ans2 == ans3);
        }
    }

}
