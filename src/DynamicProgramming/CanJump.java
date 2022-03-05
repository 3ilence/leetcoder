package DynamicProgramming;

/**
 * @ClassName : CanJump
 * @Author : Silence
 * @Date: 2022/3/5 9:44
 * @Description : 55. 跳跃游戏
 */
public class CanJump {
    /**
     * 动规解法
     * @param nums nums
     * @return true or false
     */
    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for (int i = 0; i < nums.length; i++) {
            if (!dp[i]) {
                continue;
            }
            for (int j = 1; i+j < nums.length && j <= nums[i]; j++) {
                dp[i+j] = true;
                if (i + j == nums.length - 1) {
                    return true;
                }
            }
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        System.out.println(new CanJump().canJump(new int[] {1}));
    }
}
