package DynamicProgramming;

import java.util.Arrays;

/**
 * @ClassName : LengthOfLIS
 * @Author : Silence
 * @Date: 2022/3/21 16:09
 * @Description : 300. 最长递增子序列
 */
public class LengthOfLIS {

    /**
     * 动态规划
     * @param nums nums
     * @return res
     */
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];//dp[i]以i结尾的最长子序列
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++ ) {
                if (dp[i] < dp[j] + 1 && nums[i] > nums[j]) {
                    dp[i] = dp[j] + 1;
                }
            }
            res = Math.max(dp[i], res);
        }
        return res;
    }
}
