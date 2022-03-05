package DynamicProgramming;

/**
 * @ClassName : Jump
 * @Author : Silence
 * @Date: 2022/3/5 10:51
 * @Description : 45. 跳跃游戏 II
 */
public class Jump {

    /**
     * 动规解法
     * @param nums nums
     * @return 到达终点需要的最小步数
     */
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];//dp[i]表示到达该位置使用的最小步数
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1; i+j < nums.length && j <= nums[i]; j++) {
                if (dp[i+j] == 0) {
                    dp[i+j] = dp[i] + 1;
                } else {
                    dp[i+j] = Math.min(dp[i+j], dp[i] + 1);
                }
            }
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(new Jump().jump(new int[] {1,2,3,4}));
    }
}
