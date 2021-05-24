package cn.alip8.juc;

import org.junit.Test;

/**
 * @author yaoshuai
 * @date 2021-五月-13
 */
public class TestCyclicBarrierSolver {

    @Test
    public void testSolver() {
        int[][] matrix = new int[150][150];
        for (int i = 0 ; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int) (Math.random() * 10000);
            }
        }
        Solver multiThreadSolver = new Solver(matrix);
    }
}
