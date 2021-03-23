package cn.alip8.algorithm.slidewindow;

import java.util.LinkedList;

/**
 * @author: Yao Shuai
 * @date: 2021/3/22 20:19
 */
public class MaxSlidingWindow {

    /**
     * 没啥可说的 最大滑动窗口
     * @param nums
     * @param k
     * @return
     */
    public int[] brutalMaxSlidingWindow(int[] nums, int k) {
        if (nums.length <= 0) {
            return new int[0];
        }
        int low = 0;
        int high = k;
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i <= nums.length - k; i++) {
            res[i] = findMax(nums, low + i, high +i);
        }
        return res;
    }

    private int findMax(int[] nums, int low, int high) {
        int max = nums[low];
        for (int i = low; i < high; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
        }
        return max;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k < 1 || nums.length < k) {
            return new int[0];
        }
        LinkedList<Integer> dqMax = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!dqMax.isEmpty() && nums[dqMax.peekLast()] < nums[i]) {
                dqMax.pollLast();
            }
            dqMax.add(i);
            if (dqMax.peekFirst() == i - k) {
                dqMax.pollFirst();
            }
            if (i >= k - 1) {
                res[index++] = nums[dqMax.peekFirst()];
            }
        }
        return res;
    }

}
