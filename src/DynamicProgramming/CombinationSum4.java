package DynamicProgramming;

/**
 * @ClassName : CombinationSum4
 * @Author : Silence
 * @Date: 2022/3/17 10:07
 * @Description : 377. 组合总和 Ⅳ
 */
public class CombinationSum4 {

    /**
     * 完全背包问题。但是这题有点特殊，顺序不同的序列视为不同的组合。例如1，2，2和2，1，2
     * 与518.零钱兑换一样都是完全背包
     * @param nums nums
     * @param target 目标数
     * @return 组合数
     */
    public int combinationSum4(int[] nums, int target) {
        int len = nums.length;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int j = 1; j <= target; j++) {
            for (int i = 0; i < len; i++) {
                if (j >= nums[i]) {
                    dp[j] += dp[j - nums[i]];
                }
            }
        }
        return dp[target];
    }
}
