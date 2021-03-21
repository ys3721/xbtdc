package cn.alip8.algorithm.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: Yao Shuai
 * @date: 2021/3/15 11:52
 */
public class AllStringSubsequences {

    public static List<String> subsRepeat(String s) {
        char[] str = s.toCharArray();
        String path = "";
        ArrayList<String> list = new ArrayList<>();
        process(str, 0, list, path);
        return list;
    }

    public static void process(char[] str, int index, List<String> list, String path) {
        if (index == str.length) {
            list.add(path);
            return;
        }
        process(str, index + 1, list, path);
        process(str, index + 1, list, path + String.valueOf(str[index]));
    }

    public static List<String> subsNoRepeat(String s) {
        char[] str = s.toCharArray();
        String path = "";
        Set<String> set = new HashSet<>();
        process2(str, 0, set, path);
        return new ArrayList<String>(Arrays.asList(set.toArray(new String[0])));
    }


    public static void process2(char[] str, int index, Set<String> set, String path) {
        if (index == str.length) {
            set.add(path);
            return;
        }
        process2(str, index + 1, set, path);
        process2(str, index + 1, set, path + String.valueOf(str[index]));
    }

    public static ArrayList<String> permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }
        char[] chs = str.toCharArray();
        process(chs, 0, res);
        return res;
    }

    public static void process(char[] str, int i, ArrayList<String> ans) {
        if (i == str.length) {
            ans.add(String.valueOf(str));
        }
        for (int j = i; j < str.length; j++) {
            swap(str, i, j);
            process(str, i + 1, ans);
            swap(str, i, j);
        }
    }

    public static void swap(char[] chars, int i , int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    public static void main(String[] args) {
        String test = "pcrd";
        List<String> ans1 = subsRepeat(test);
        System.out.println(ans1.contains(""));
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("------------------");
        List<String> ans2 = permutation(test);
        for (String str2 : ans2) {
            System.out.println(str2);
        }
    }

}
