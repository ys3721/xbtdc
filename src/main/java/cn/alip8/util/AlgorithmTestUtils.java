package cn.alip8.util;

import java.sql.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

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

    public static int[] genLargeThenZeroNoRepeat(int maxLen, int max) {
        int[] arr = generateArray(maxLen, max, false);
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                set.add(arr[i]);
            }
        }
        int[] noRepeat = new int[set.size()];
        int i = 0;
        for (Iterator<Integer> it = set.iterator(); it.hasNext(); ) {
            noRepeat[i++] = it.next();
        }
        return noRepeat;
    }
}
