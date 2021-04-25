package cn.alip8.algorithm.cube;

/**
 * 一行正方形，已经染成红或绿，现在可以给任何一个块儿染色，那么最小染色几个可以使
 * RRGGRGGGGR这样的一个排在红色在左，绿色在右侧。
 * 比如：GGGGR 最少要涂两个  GGGRGR需要改两个
 *
 * @author: Yao Shuai
 * @date: 2021/4/25 15:10
 */
public class CubeColor {

    //GGRGRG - > RRRGGG
    public static  int bruteFindMin(String target) {
        //枚举每一个分界线
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < target.length() + 1; i++) {
            int leftG = 0, rightR = 0;
            for (int j = 0; j < target.length(); j++) {
                if (j < i) {
                    if (target.charAt(j) == 'G') {
                        leftG ++;
                    }
                }
                if (j >= i) {
                    if (target.charAt(j) == 'R') {
                        rightR ++;
                    }
                }
            }
            min = Math.min(min, leftG + rightR);
        }
        return min;
    }

    public static int quickFindMin(String redGreenCube) {
        int totalR = 0;
        for (char c : redGreenCube.toCharArray()) {
            totalR += c == 'R' ? 1 : 0;
        }
        int leftG = 0;
        int min = totalR;
        for (int i = 0; i < redGreenCube.length(); i++) {
            leftG += redGreenCube.charAt(i) == 'G' ? 1 : 0;
            totalR -= redGreenCube.charAt(i) == 'G' ? 0 : 1;
            min = Math.min(min, leftG + totalR);
        }
        return min;
    }
}
