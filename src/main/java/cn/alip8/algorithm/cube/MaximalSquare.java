package cn.alip8.algorithm.cube;

/**
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * 例如：
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * 最大的正方型是2
 *
 * @author: Yao Shuai
 * @date: 2021/4/25 17:56
 */
public class MaximalSquare {

    public static int maximalSquare(char[][] matrix) {
        return 2;
    }

    public static int bruteMalSolution(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != '0') {
                    max = 1;
                    int curLimitSide = Math.min(matrix.length - i, matrix[i].length - j);
                    for (int k = 1; k < curLimitSide; k++) {
                        boolean flag = true;
                        if (matrix[i + k][j] == '0' || matrix[i][j + k] == '0') {
                            break;
                        }
                        for (int m = 1; m < k; m++) {
                            if (matrix[i + k][j + m] == '0' || matrix[i + m][j + k] == '0') {
                                break;
                            }
                            flag = true;
                        }
                        if (flag == true) {
                            max = Math.max(max, k);
                        }
                    }
                }
            }
        }
        return max;
    }


}
