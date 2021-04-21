package cn.alip8.algorithm;

import cn.alip8.algorithm.parentheses.LongestValidParentheses;
import cn.alip8.algorithm.parentheses.ParenthesesValidate;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: Yao Shuai
 * @date: 2021/4/19 10:35
 */
public class ParenthesisTest {

    @Test
    public void testValidate() {
        Assert.assertTrue("单个的括号肯定是合法的啦", ParenthesesValidate.isValid("()"));
        Assert.assertTrue("这个肯定肯定是不合法的啦", !ParenthesesValidate.isValid("(){}}{"));
        Assert.assertTrue("单个的括号肯定是合法的啦", !ParenthesesValidate.isValid("([)]"));
     }

     @Test
     public void testMaxSubParentheses() {
         Assert.assertTrue("2", LongestValidParentheses.maxLength("())") == 2);
         Assert.assertTrue("6", LongestValidParentheses.maxLength("()(())") == 6);
     }
}
