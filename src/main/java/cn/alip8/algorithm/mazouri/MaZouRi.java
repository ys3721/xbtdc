package cn.alip8.algorithm.mazouri;

import java.sql.SQLOutput;

/**
 * @author: Yao Shuai
 * @date: 2021/4/6 10:59
 * <p>
 * 马从[0,0]位置开始跳，想要跳到k点[x, y], 问跳k步的话，有几种跳法.
 * 中国象棋竖向轴定为x 0-9  纵轴y 0-8. 对于这个题目哪个横纵无所谓，因为棋盘横着放也一样
 */
public class MaZouRi {

    public static int ways(int x, int y, int k) {
        return f(x, y, k);
    }

    /**
     * k步数到这个点的可能走法数量
     *
     * @param x 目标x轴
     * @param y 目标y轴
     * @param k 要走k步
     * @return
     */
    public static int f(int x, int y, int k) {
        //还有k步要走到x y，达到目标x y还有几种方法？
        //没有步数了，但是正好在这个位置，那么就是一种走法 不用动算是一种走法吧。。
        if (k == 0) {
            return x == 0 && y == 0 ? 1 : 0;
        }
        /* 如果只能走一步的话 那么只有临近的八个点能够走到x，y位置
            （x +- 2）  （y +- 1） 或者 （y +- 2） （x +- 1）
         */
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        //有步数走
        return f(x + 2, y - 1, k - 1)
                + f(x + 2, y + 1, k - 1)
                + f(x - 2, y + 1, k - 1)
                + f(x - 2, y - 1, k - 1)
                + f(x + 1, y + 2, k - 1)
                + f(x + 1, y - 2, k - 1)
                + f(x - 1, y + 2, k - 1)
                + f(x - 1, y - 2, k - 1);
    }

    public static void main(String[] args) {
        int x = 6;
        int y = 8;
        int k = 12;
        long time = System.currentTimeMillis();
        System.out.println(ways(x, y, k));
        System.out.println(System.currentTimeMillis() - time);
        time = System.currentTimeMillis();
        System.out.println(waysDp(x, y, k));
        System.out.println(System.currentTimeMillis() - time);
    }

    public static int waysDp(int x, int y, int k) {
        int[][][] dp = new int[10][9][k + 1];
        dp[0][0][0] = 1;

        for (int level = 1; level <= k; level++) {
            for (int i = 0; i < 10; i ++) {
                for (int j = 0; j < 9; j++) {
                    dp[i][j][level] = getValue(dp, i + 2, j - 1, level - 1)
                            + getValue(dp, i + 2, j + 1, level - 1)
                            + getValue(dp, i - 2, j + 1, level - 1)
                            + getValue(dp, i - 2, j - 1, level - 1)
                            + getValue(dp, i + 1, j + 2, level - 1)
                            + getValue(dp, i + 1, j - 2, level - 1)
                            + getValue(dp, i - 1, j + 2, level - 1)
                            + getValue(dp, i - 1, j - 2, level - 1);
                }
            }
        }
        return dp[x][y][k];
    }

    public static int getValue(int[][][] dp, int x, int y, int k) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return dp[x][y][k];
    }
}
