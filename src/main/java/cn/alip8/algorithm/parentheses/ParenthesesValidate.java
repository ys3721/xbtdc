package cn.alip8.algorithm.parentheses;

import java.util.Stack;

/**
 * (1) Parentheses 小括号；圆括号（复数）
 * Make sure to read what's in parentheses.
 * 一定要读一下括号里的内容。
 * Open / Close parenthesis 小括号；圆括号（单数）
 * Write this down: all new students will need to come to the Conference Hall, open parenthesis, Room 202, close parenthesis.
 * 写下来：所有新生需要到会议室，括号，202室，括号完。
 *
 *  <p/>(2) Brackets 中括号；方括号
 * We type suggestions between brackets to distinguish them from the regular text.
 * 我们会把建议写在中括号里，以区别于正文。
 *
 * <p/>(3) Curly brackets
 * = Opening / closing braces
 * 大括号
 * The curly brackets contain a translation of the Spanish terms in the article.
 * 大括号里是文章里一些西班牙语词的翻译。
 * Make sure to put opening and closing braces around the equation.
 * 一定要在等式两边写上大括号。
 *
 * @author: Yao Shuai
 * @date: 2021/4/19 10:23
 */
public class ParenthesesValidate {

    public static  boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char t = s.charAt(i);
            if (t == '(' || t == '[' || t == '{') {
                stack.push(t);
            } else {
                if (stack.empty()) {
                    return false;
                }
                char top = stack.pop();
                if (t == ')') {
                    if (top != '(') {
                        return false;
                    }
                }
                if (t == '}') {
                    if (top != '{') {
                        return false;
                    }
                }
                if (t == ']') {
                    if (top != '[') {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    public static boolean validateByCounter(String s) {
        //暂时不用栈的话我还是不是很会算。
        return false;
    }
}
