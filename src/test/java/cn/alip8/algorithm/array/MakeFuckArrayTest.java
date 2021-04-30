package cn.alip8.algorithm.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: Yao Shuai
 * @date: 2021/4/28 13:56
 */
public class MakeFuckArrayTest {

    @Test
    public void testGenNo2SumArray(int[] arr) {
        for (int i = 0; i < arr.length ; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    Assert.assertTrue(arr[i] + arr[k] != 2* arr[j]);
                }
            }
        }
    }

    @Test
    public void fdslkkj() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(1);
            //this.testGenNo2SumArray(AlgorithmTestUtils.generateArray(100, 100000, false));
        }
    }
}
