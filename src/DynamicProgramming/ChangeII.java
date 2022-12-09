package DynamicProgramming;

/**
 * @ClassName : ChangeII
 * @Author : Silence
 * @Date: 2022/12/8 21:38
 * @Description : 518. 零钱兑换 II
 */
public class ChangeII {

    /**
     * 正确做法
     * @param amount
     * @param coins
     * @return
     */
    public int change2(int amount, int[] coins) {
        int len = coins.length, res = 0;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int j = 0; j < len; j++) {
            for (int i = 1; i <= amount; i++) {
                if (i >= coins[j]) {
                    dp[i] += dp[i - coins[j]];
                }
            }
        }
        return dp[amount];
    }

    /**
     * 错误做法，这种做法会有重复，比如1，2，1和1，1，2都被算进去了，分别通过dp[4] += dp[4 - 1]和dp[4] = dp[4 - 2]算进来
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int len = coins.length, res = 0;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < len; j++) {
                if (i >= coins[j]) {
                    dp[i] += dp[i - coins[j]];
                }
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        new ChangeII().change(5, new int[] {1, 2, 5});
    }
}
