package cn.alip8.algorithm.cube;

import net.sf.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author: Yao Shuai
 * @date: 2021/4/26 20:06
 */
public class MaximalSquareTest {

    @Test
    public void testMaximalSquare() {
        //String square = "[[\"1\",\"1\",\"1\",\"1\",\"1\"],[\"1\",\"1\",\"1\",\"1\",\"1\"],[\"1\",\"1\",\"1\",\"1\",\"1\"],[\"1\",\"1\",\"1\",\"1\",\"1\"]]";
        String square = "[[\"1\",\"0\",\"1\",\"0\",\"0\"],[\"1\",\"0\",\"1\",\"1\",\"1\"],[\"1\",\"1\",\"1\",\"1\",\"1\"],[\"1\",\"0\",\"0\",\"1\",\"0\"]]";
        JSONArray jsonArray = JSONArray.fromObject(square);
        int x  = jsonArray.size();
        int y = jsonArray.getJSONArray(0).size();
        char[][] squareChar = new char[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y ; j++) {
                squareChar[i][j] = jsonArray.getJSONArray(i).getString(j).charAt(0);
            }
        }
        int maxBianchang = MaximalSquare.bruteMalSolutionQuestion1(null);
        Assert.assertTrue(   maxBianchang+"" ,maxBianchang==2);
    }


}
