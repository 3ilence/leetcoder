package DynamicProgramming;

import java.util.List;

/**
 * @ClassName : WordBreak
 * @Author : Silence
 * @Date: 2022/3/19 12:49
 * @Description : 139.单词拆分
 */
public class WordBreak {

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
