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
    public void operatorPrecedenceTest() {
        System.out.println(2 + (6 - 2 >> 1));
        System.out.println(2 + ((6 - 2) >> 1));
    }

    @Test
    public void testSelectSort() {
        for (int j = 0; j < 1000000 ; j++) {
            int[] target = AlgorithmTestUtils.generateArray(10, 100, false);
            int[] targetModel = Arrays.copyOf(target, target.length);
            SelectSort selectSort = new SelectSort();
            selectSort.selectSort(target);
            Arrays.sort(targetModel);
            Assert.assertTrue(Arrays.equals(target, targetModel));

            int _randTarget = (int) (Math.random() * 10000);
            int _findIndex = selectSort.binarySearch(target, _randTarget);
            if (_findIndex >=0) {
                Assert.assertTrue(target[_findIndex] == _randTarget);
            } else {
                Assert.assertTrue(!Arrays.asList(target).contains(_randTarget));
            }

            int index = selectSort.bsFirstLarge(target, _randTarget);

            if (index > 0) {
                Assert.assertTrue(Arrays.toString(target)+" target="+_randTarget+" index="+index,target[index] >= _randTarget && target[index - 1] < _randTarget);
            } else if (index == 0){
                Assert.assertTrue(target[index] >= _randTarget);
            } else {
                Assert.assertTrue(target[0] < _randTarget);
            }

        }
    }
}
