package DynamicProgramming;

/**
 * @ClassName : IntegerBreak
 * @Author : Silence
 * @Date: 2022/3/5 19:51
 * @Description : 343. 整数拆分
 */
public class IntegerBreak {

    /**
     * 动态规划。dp[i] = Max(dp[j] * dp[i-j]），(j >= 1 && j <= i / 2)。时间复杂度O(n2)。
     * @param n n
     * @return 分割后的最大乘积
     */
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 1;
        if (n <= 2) {
            return dp[n];
        }

        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * Math.max(dp[i-j], i-j));
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new IntegerBreak().integerBreak(10));
    }
}
