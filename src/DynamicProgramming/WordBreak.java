package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : WordBreak
 * @Author : Silence
 * @Date: 2022/3/19 12:49
 * @Description : 139.单词拆分
 */
public class WordBreak {

    public static void main(String[] args) {
        new WordBreak().wordBreak3("dogs", new ArrayList<>(){{
            add("dog");add("s");add("gs");
        }});
    }

    public boolean wordBreak3(String s, List<String> wordDict) {
        int len = s.length();
        int size = wordDict.size();
        boolean[] dp = new boolean[len + 1];//dp[i][j]表示前j个word能否组成s的前i个字符
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < size; j++) {
                if (match(s, i - 1, wordDict.get(j))) {
                    //因为是boolean数组，这样直接赋值，true可能会被false覆盖
                    //所以应该只有当dp[i] == false的时候才进行状态覆盖
                    dp[i] = dp[i - wordDict.get(j).length()];
                }
            }
        }
        return dp[len];
    }

    public boolean match(String s, int end, String word) {
        int count = 0;
        for (int i = end, j = word.length() - 1; i >= 0 && j >= 0; i--,j--) {
            if (s.charAt(i) != word.charAt(j)) {
                return false;
            } else {
                count++;
            }
        }
        return count == word.length();
    }

    // <https://leetcode-cn.com/problems/word-break/>
    /**
     * 完全背包，动态规划
     * @param s s
     * @param wordDict wordDict
     * @return res
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];//dp[i]代表substring(0, i)能否被构成
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (String str : wordDict) {
                if (i >= str.length()) {
                    if (s.substring(0, i).endsWith(str)) {
                        if (!dp[i]) {
                            dp[i] =  dp[i - str.length()];
                        }
                    }
                }
            }
        }
        return dp[len];
    }

    /**
     * 递归，超时
     * @param s s
     * @param wordDict wordDict
     * @return res
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }
        for (String str : wordDict) {
            if (s.startsWith(str)) {
                boolean tmp = wordBreak2(s.substring(str.length(), s.length()), wordDict);
                if (tmp) {
                    return true;
                }
            }
        }
        return false;
    }
}
