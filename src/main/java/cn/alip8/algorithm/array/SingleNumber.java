package cn.alip8.algorithm.array;

/**
 * @author: Yao Shuai
 * @date: 2021/5/19 20:10
 */
public class SingleNumber {

    /**
     * 136
     * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
     *
     * @return
     */
    public int singleOne(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result ^= arr[i];
        }
        return  result;
    }

    /**
     * 137
     * Given an integer array nums where every element appears three times except for one, which appears exactly once.
     * Find the single element and return it.
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int maskValue = 0;
            for (int value : nums) {
                maskValue += ((value >> i) & 1);
            }
            if (maskValue % 3 != 0) {
                result |= (1 << i);
            }
        }
        return result;
    }

}
