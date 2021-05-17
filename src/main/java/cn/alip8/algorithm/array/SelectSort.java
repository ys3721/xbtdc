package cn.alip8.algorithm.array;

/**
 * @author: Yao Shuai
 * @date: 2021/5/7 11:43
 */
public class SelectSort {

    public void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minValueIndex = i;
            for (int j = i; j < array.length; j++) {


                if (array[j] < array[minValueIndex]) {
                    minValueIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minValueIndex];
            array[minValueIndex] = temp;
        }
    }

    public int binarySearch(int[] sortedArr, int target) {
        int low = 0;
        int high = sortedArr.length - 1;
        while (low <= high) {
           int middle = low + ((high - low) >> 1);
           if (sortedArr[middle] > target) {
               high = middle - 1;
           } else if (sortedArr[middle] < target) {
               low = middle + 1;
           } else {
               return middle;
           }
        }
        return -1;
    }

    /**
     * 在一个有序数组中查找， 大于等于某个数最左侧的位置
     * 列如 12222233334444555555 大于等于2的最左侧位置
     *      ^
     * 结果是这个值
     *
     * @param dupSortArr
     * @param target
     * @return
     */
    public int bsFirstLarge(int[] dupSortArr, int target) {
        int low = 0;
        int high = dupSortArr.length - 1;
        int lastResult = -1;
        while (low <= high) {
            int middle = low + ((high - low) >> 1);
            if (dupSortArr[middle] >= target) {
                lastResult = middle;
                high = middle - 1;
            } else if (dupSortArr[middle] < target) {
                low = middle + 1;
            }
        }
        return lastResult;
    }

}
