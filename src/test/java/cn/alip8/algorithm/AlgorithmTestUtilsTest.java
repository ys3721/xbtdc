package cn.alip8.algorithm;

import cn.alip8.util.AlgorithmTestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * @author: Yao Shuai
 * @date: 2021/4/13 11:16
 */
public class AlgorithmTestUtilsTest {

    @Test
    public void testGeneralArray() {
        Random random = new Random();
        int maxLen = random.nextInt(100000);
        int maxValue = Math.abs(random.nextInt());
        boolean needNegative = Math.random() > 0.5f;
        for (int j = 0; j < 1; j++) {
            int[] arr = AlgorithmTestUtils.generateArray(maxLen, maxValue, needNegative);
            Assert.assertTrue("长度居然比最大长度都长了 array length = " + arr.length,
                    arr.length <= maxLen);
            for (int value : arr) {
                Assert.assertTrue(String.format("生成的值%s大于要求的最大值%s", value, maxValue),
                        value <= maxValue);
                if (!needNegative) {
                    Assert.assertTrue(String.format("不要负数", value, maxValue),
                            value <= maxValue);
                }
            }
        }
    }

    @Test
    public void testGenBigArray() {
        Random random = new Random();
        int maxLen = random.nextInt((int) Math.pow(2, 20)) + 100000;
        int maxValue = Math.abs(random.nextInt(99999));
        boolean needNegative = Math.random() > 0.5f;
        for (int j = 0; j < 1; j++) {
            int[] arr = AlgorithmTestUtils.generateArray(maxLen, maxValue, needNegative);
            Assert.assertTrue("长度居然比最大长度都长了 array length = " + arr.length,
                    arr.length <= maxLen);
            for (int value : arr) {
                Assert.assertTrue(String.format("生成的值%s大于要求的最大值%s", value, maxValue),
                        value <= maxValue);
            }
        }
    }
}
