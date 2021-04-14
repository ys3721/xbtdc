package cn.alip8.util;

import java.util.Arrays;

/**
 * @author: Yao Shuai
 * @date: 2021/4/13 10:44
 */
public class AlgorithmTestUtils {

    public static int[] generateArray(int maxLen, int maxValue, boolean needNegative) {
        if (maxLen <= 0) {
            return new int[0];
        }
        maxValue = Math.abs(maxValue);
        int[] arr = new int[(int) (Math.random() * maxLen + 1)];
        for (int i = 0; i < arr.length; i++) {
            if (needNegative) {
                if (Math.random() > 0.5) {
                    arr[i] = -(int) (Math.random() * maxValue);
                } else {
                    arr[i] = (int) (Math.random() * maxValue);
                }
            } else {
                arr[i] = (int) (Math.random() * maxValue);
            }
        }
        return arr;
    }

    public static int[] generateSortedArray(int maxLen, int max, boolean needNegative) {
        int[] arr = generateArray(maxLen, max, needNegative);
        Arrays.sort(arr);
        return arr;
    }
}
