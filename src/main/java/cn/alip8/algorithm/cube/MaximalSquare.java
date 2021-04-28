package cn.alip8.algorithm.cube;

/**
 * leetcode 1139 下面代号1.
 * 最大边为1的正方形，内部可以为空，只要求边都是1.
 * leetcode 1139. 最大的以 1 为边界的正方形
 * 给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：9
 * 示例 2：
 *
 * 输入：grid = [[1,1,0,0]]
 * 输出：1
 *
 * 2.最大正方形，但是要求内部也都是1.
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

    public static int bruteMalSolutionQuestion1(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    max = Math.max(max, 1);
                    int maxBianChang = Math.min(grid.length - i, grid[i].length - j);
                    for (int bianchang = 2; bianchang <= maxBianChang; bianchang++) {
                        boolean flag = true;
                        if (grid[i + bianchang - 1][j] == 0 || grid[i][j + bianchang - 1] == 0) {
                            break;
                        }
                        for (int m = 0; m < bianchang; m++) {
                            if (grid[i + bianchang - 1][j + m] == 0 || grid[i + m][j + bianchang - 1] == 0) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            max = Math.max(max, bianchang);
                        }
                    }
                }
            }
        }
        return (int) Math.pow(max, 2);
    }


}
