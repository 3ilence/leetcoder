package DynamicProgramming;

/**
 * @ClassName : CountSubstrings
 * @Author : Silence
 * @Date: 2022/12/6 13:53
 * @Description : 647. 回文子串
 */
public class CountSubstrings {

    /**
     * 和516.最长回文子序列一样的思路，但是要更简单，因为子串相比子序列确定性更强，思考起来更简单，但是二者dp数组以及做法可以说一模一样
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        // 中心扩展
        int len = s.length(), res = 0;
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            res += 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (i + 1 > j - 1) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                res += dp[i][j] > 0 ? 1 : 0;
            }
        }
        return res;
    }
}
