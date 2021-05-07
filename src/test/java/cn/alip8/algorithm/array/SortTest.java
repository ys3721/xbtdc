package cn.alip8.algorithm.array;

import cn.alip8.util.AlgorithmTestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author: Yao Shuai
 * @date: 2021/5/7 11:51
 */
public class SortTest {

    @Test
    public void testSelectSort() {
        for (int j = 0; j < 100 ; j++) {
            int[] target = AlgorithmTestUtils.generateArray(10000, 10000000, false);
            int[] targetModel = Arrays.copyOf(target, target.length);
            SelectSort selectSort = new SelectSort();
            selectSort.selectSort(target);
            Arrays.sort(targetModel);
            Assert.assertTrue(Arrays.equals(target, targetModel));
        }

    }
}
