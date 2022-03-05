package GreedyAlgorithm;

/**
 * @ClassName : CanJump
 * @Author : Silence
 * @Date: 2022/3/5 9:44
 * @Description : 55. 跳跃游戏
 */
public class CanJump {
    /**
     * 贪心解法
     * @param nums nums
     * @return true or false
     */
    public boolean canJump(int[] nums) {
        //这题没必要用一个dp数组来记录状态，因为我们只关注最远能到达哪里
        //只要当前遍历元素在maxLength范围内都是有效的
        //一旦maxLength等于数组长度立即返回true
        int maxLength = 0;//所能到达的最长距离
        for (int i = 0; i < nums.length; i++) {
            if (i <= maxLength) {
                maxLength = Math.max(maxLength, i + nums[i]);
            }
            if (maxLength >= nums.length - 1) {
                return true;
            }
        }
        return maxLength >= nums.length - 1;
    }

    public static void main(String[] args) {
        System.out.println(new CanJump().canJump(new int[] {1}));
    }
}
