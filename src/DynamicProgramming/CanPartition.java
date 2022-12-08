package DynamicProgramming;

/**
 * @ClassName : CanPartition
 * @Author : Silence
 * @Date: 2022/3/10 9:34
 * @Description : 416. 分割等和子集
 */
public class CanPartition {

    /**
     * 背包问题一般都是倒序进行状态转移可以完成状态压缩
     * @param nums
     * @return
     */
    public boolean canPartition3(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 != 0) {
            return false;
        }
        boolean[][] dp = new boolean[len + 1][sum / 2 + 1];//dp[i][j]表示前i个元素能否拼出j
        for (int i = 0; i <= len; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= sum / 2; j++) {
                if (j >= nums[i - 1]) {
                    if (dp[i-1][j - nums[i - 1]] || dp[i-1][j]) {
                        dp[i][j] = true;
                    }
                } else {
                    dp[i][j] = dp[i-1][j];
                }
                if (j == sum / 2 && dp[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 01背包问题，动规解法，二维状态数组
     * @param nums nums
     * @return 能否分割
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 != 0) {
            return false;
        }
        //问题变为在数组中选取几个数，使得和为sum/2
        int[][] dp = new int[sum/2 + 1][nums.length];//dp[i][j]表示从0-i的数中选取放入容量为j的背包能获得的最大价值

        for (int i = 1; i <= sum / 2; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i < nums[j]) {
                    dp[i][j] = j-1 >= 0 ? dp[i][j-1] : 0;//需要注意一下这里，即便nums[j] > i，即重量比背包容量大，并不是直接赋值0，而是等于dp[i][j-1]
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = nums[j];
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-nums[j]][j- 1] + nums[j] );
                }
                if (dp[i][j] == sum / 2) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 01背包问题，一维状态数组(优化或者说状态压缩)
     * @param nums nums
     * @return 能否分割
     */
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 != 0) {
            return false;
        }
        //问题变为在数组中选取几个数，使得和为sum/2
        int[] dp = new int[sum/2 + 1];//放入容量为j的背包能获得的最大价值

        for (int i = 0; i < nums.length; i++) {
            //倒序遍历，dp[j]中的j表示还剩多少空间
            for (int j = sum / 2; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
                if (dp[j] == sum / 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new CanPartition().canPartition(new int[] {1,5,10,6}));
    }
}
