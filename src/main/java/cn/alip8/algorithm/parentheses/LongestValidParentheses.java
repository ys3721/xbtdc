package cn.alip8.algorithm.parentheses;

/**
 *  返回一个括号字符串中，最长的括号有效子串的长度
 *
 * @author: Yao Shuai
 * @date: 2021/4/19 12:01
 */
public class LongestValidParentheses {

    public static int maxLength(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        int max = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                int pair = i - 1 - dp[i - 1];
                if (pair >= 0 && s.charAt(pair) == '(') {
                    dp[i] = dp[i-1] + 2 + (pair - 1 < 0 ? 0 : dp[pair - 1]);
                    if (max < dp[i]) {
                        max = dp[i];
                    }
                }
            }
        }
        return max;
    }

}
