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
}
