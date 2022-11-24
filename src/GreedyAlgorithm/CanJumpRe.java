package GreedyAlgorithm;

/**
 * @ClassName : CanJumpRe
 * @Author : Silence
 * @Date: 2022/11/24 14:33
 * @Description : 55. 跳跃游戏
 */
public class CanJumpRe {
    public boolean canJump(int[] nums) {
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= maxLength) {
                maxLength = Math.max(maxLength, i + nums[i]);
            }
        }
        return maxLength >= nums.length - 1;
    }
}
