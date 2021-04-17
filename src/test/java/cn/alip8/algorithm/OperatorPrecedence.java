package cn.alip8.algorithm;

import org.junit.Test;

/**
 * @author: Yao Shuai
 * @date: 2021/4/17 14:43
 */
public class OperatorPrecedence {

    @Test
    public void ternaryOperator() {
        String a = "a";
        a += a == null ? "null" : "notNull";
        System.out.println(a);
    }


}
