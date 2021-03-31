package cn.alip8.algorithm.slidewindow;

/**
 * @author: Yao Shuai
 * @date: 2021/3/25 12:08
 */
public class AllLessNumSubArray {

    public static int getNumberBao(int[] arr, int num) {
        if (arr == null | arr.length == 0) {
            return 0;
        }
        int valid = 0;
        for (int l = 0; l < arr.length; l++) {
            //l ..l  l...l+1
            for (int r = l; r < arr.length; r ++) {
                //arr[l..r]
                int max = 0;
                int min = 0;
                for (int i = l; i <= r; i++) {
                    if (max < arr[i]) {
                        max = arr[i];
                    }
                    if (min > arr[i]) {
                        min = arr[i];
                    }
                }
                if (max - min <= num) {
                    valid++;
                }
            }
        }
        return valid;
    }

}
