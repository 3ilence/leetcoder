package DynamicProgramming;

/**
 * @ClassName : FindTargetSumWays
 * @Author : Silence
 * @Date: 2022/3/14 10:57
 * @Description : 494. 目标和
 */
public class FindTargetSumWays {

    /**
     * 动规，空间优化
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays3(int[] nums, int target) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if ((sum + target) % 2 == 1 || (sum + target) < 0) {
            return 0;
        }
        target = (sum + target) / 2;
        int len = nums.length, res = 0;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int j = target; j >= 0; j--) {
                if (j >= nums[i - 1]) {
                    dp[j] = dp[j] + dp[j - nums[i - 1]];
                } else {
                    dp[j] = dp[j];
                }
            }
        }
        return dp[target];
    }

    /**
     * 动规，空间未优化
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if ((sum + target) % 2 == 1 || (sum + target) < 0) {
            return 0;
        }
        target = (sum + target) / 2;
        int len = nums.length, res = 0;
        int[][] dp = new int[len + 1][target + 1];//dp[i][j]表示在数组前i个元素中挑选，能使和为j的组合的种数
        dp[0][0] = 1;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= target; j++) {
                if (j >= nums[i - 1]) {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j - nums[i - 1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[len][target];
    }

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
