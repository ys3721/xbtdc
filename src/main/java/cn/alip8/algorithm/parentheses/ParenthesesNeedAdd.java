package cn.alip8.algorithm.parentheses;

/**
 * Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting parentheses string is valid.
 * <p>
 * Formally, a parentheses string is valid if and only if:
 * <p>
 * It is the empty string, or
 * It can be written as AB(A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Yao Shuai
 * @date: 2021/4/19 11:42
 */
public class ParenthesesNeedAdd {

    public int minAddToMakeValid(String s) {
        int countOfOpen = 0, needClose = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                countOfOpen++;
            } else {
                if (countOfOpen == 0) {
                    needClose++;
                } else {
                    countOfOpen--;
                }
            }
        }
        return countOfOpen + needClose;
    }

}
