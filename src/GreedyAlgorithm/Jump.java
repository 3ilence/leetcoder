package GreedyAlgorithm;

/**
 * @ClassName : Jump
 * @Author : Silence
 * @Date: 2022/3/5 10:51
 * @Description : 45. 跳跃游戏 II
 */
public class Jump {

    /**
     * 贪心解法
     * @param nums nums
     * @return 到达终点需要的最小步数
     */
    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    public static void main(String[] args) {
        System.out.println(new Jump().jump(new int[] {1,2,3,4}));
    }
}
