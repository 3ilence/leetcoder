package DynamicProgramming;

/**
 * @ClassName : FindTargetSumWays
 * @Author : Silence
 * @Date: 2022/3/14 10:57
 * @Description : 494. 目标和
 */
public class FindTargetSumWays {

    /**
     * 01背包，空间未优化
     * @param nums nums
     * @param target target
     * @return res
     */
    public int findTargetSumWays1(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.length, neg = diff / 2;
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];
    }

    /**
     * 01背包，空间优化
     * @param nums nums
     * @param target target
     * @return res
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int len = nums.length, neg = diff / 2;
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for (int i = 0; i < len; i++) {
            for (int j = neg; j >= 0; j--) {
                if (j >= nums[i]) {
                    dp[j] += dp[j-nums[i]];
                }
            }
        }
        return dp[neg];
    }

    public static void main(String[] args) {
        System.out.println(new FindTargetSumWays().findTargetSumWays(new int[] {1,1,1,1,1}, 1));
    }
}
