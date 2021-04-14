package cn.alip8.algorithm.slidewindow;

/**
 * 给定一个数组，里面都是有序的正整数，代表距离。
 * 给定一个长度L，问L能覆盖的最大的点是多少个。
 * 比如一个数组里面是一个线性距离，类似高速公路服务器（无加油站..额），L是能跑多远的油量，
 * 问最多路过几个服务区。出发地点可以随意，但是如果从服务区出发那么直接就算路过一个。
 *
 * @author: Yao Shuai
 * @date: 2021/4/13 10:31
 */
public class CordCoverMaxPoint {

    /**
     * 暴力解，每个都走一遍。
     * 从第一个服务区出发，遍历到最后。
     * 这里有个必然的贪心，必然压着服务区出发能覆盖最多。
     *
     * @param arr
     * @param l
     * @return
     */
    public static int maxPoint0(int[] arr, int l) {
        int maxCount = 0;
        for (int i = 0; i < arr.length; i++) {
            int source = arr[i];
            int fastValue = source + l;
            int count = findCount(i, fastValue, arr);
            if (maxCount < count) {
                maxCount = count;
            }
        }
        return maxCount;
    }

    private static int findCount(int sourceIndex, int fastValue, int[] arr) {
        int count = 1;
        for (int i = sourceIndex; i < arr.length - 1; i++ ) {
            if (arr[i + 1] <= fastValue) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * 也可以从绳子尾部，倒着往前找(相当于倒着往前开车)。arr[i]-l的起点，找到比max大等的第一个索引，用当前索引减去找到的索引+1个点
     * 其中找大于等于maxRange的第一个点可以用二分查找
     * @param arr
     * @param l
     * @return
     */
    public static int maxPoint1(int[] arr, int l) {
        int maxPoint = 0;
        for (int i = 0; i < arr.length; i++) {
            int maxRange = arr[i] - l;
            int curMaxPoint = i - binaryFindMaxIndex(maxRange) + 1;
            if (maxPoint < curMaxPoint) {
                maxPoint = curMaxPoint;
            }
        }
        return maxPoint;
    }

    /**
     * 查找数组中第一个比maxrange大的点索引
     *
     * @param maxRange
     * @return
     */
    private static int binaryFindMaxIndex(int[] arr, int endIndex, int maxRange) {
        if (endIndex <= 0) {

        }
        return 0;
    }

    private static int binarySearch(int[] arr, int target) {
        if (arr.length == 0) {
            return -1;
        }
        int result = -1;
        int low = 0;
        int high = arr.length - 1;
        int midIndex = low + (high - low) / 2;
        while (low <= high) {
            if (target > arr[midIndex]) {
                low = midIndex + 1;
            } else if (target < arr[midIndex]) {
                high = midIndex - 1;
            } else {
                result = midIndex;
                break;
            }
        }
        return midIndex;
    }

    public static int maxPoint2(int[] arr, int l) {
        return 0;
    }

    public static int maxPoint3(int[] arr, int l) {
        return 0;
    }


}
