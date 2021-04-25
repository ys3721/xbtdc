package cn.alip8.algorithm.cube;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

/**
 * @author: Yao Shuai
 * @date: 2021/4/25 15:30
 */
public class CubeTest {

    private static String redGreenCube;

    private static int bruteMin;

    private static int quickMin;

    @BeforeClass
    public static void prepareOnce() {
        int count = (int) (Math.random() * 100);
        redGreenCube = RandomStringUtils.random(count, new char[]{'R', 'G'});
    }

    @Test
    public void testBrute() {
        bruteMin = CubeColor.bruteFindMin(redGreenCube);
    }

    @Test
    public void testQuick() {
        quickMin = CubeColor.quickFindMin(redGreenCube);
    }

    @AfterClass public static void compareSame() {
        Assert.assertTrue("结果应该是一样的", bruteMin == quickMin);
    }

}
