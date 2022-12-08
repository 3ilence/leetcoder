package DynamicProgramming;

/**
 * @ClassName : LastStoneWeightII
 * @Author : Silence
 * @Date: 2022/3/10 10:41
 * @Description : 1049. 最后一块石头的重量 II
 */
public class LastStoneWeightII {

    /**
     * 空间压缩后
     * @param stones
     * @return
     */
    public int lastStoneWeightII4(int[] stones) {
        //从这对石头里选出最接近sum/2的重量
        int sum = 0;
        for (int i : stones) {
            sum += i;
        }
        int target = sum / 2;//如果不能整除会截断
        int len = stones.length, res = 0;
        boolean[] dp = new boolean[target + 1];//dp[i][j]代表前i个石头能否达到重量j
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            //注意空间压缩后需要倒序进行状态转移，如果正序的话dp[i-1][j - stones[i-1]]被dp[i][j - stones[i-1]]覆盖
            //相当于把dp[i][j - stones[i-1]]当成dp[i-1][j - stones[i-1]]来用了
            for (int j = target; j >= stones[i - 1]; j--) {
                if (dp[j - stones[i - 1]]) {
                    dp[j] = true;
                }
                if (dp[j]) {
                    res = Math.max(res, j);
                }
            }
        }
        return res;
        //return Math.abs(res * 2 - sum);
    }

    /**
     * 空间压缩前
     * @param stones
     * @return
     */
    public int lastStoneWeightII3(int[] stones) {
        //从这对石头里选出最接近sum/2的重量
        int sum = 0;
        for (int i : stones) {
            sum += i;
        }
        int target = sum / 2;//如果不能整除会截断
        int len = stones.length, res = 0;
        boolean[][] dp = new boolean[len + 1][target + 1];//dp[i][j]代表前i个石头能否达到重量j
        for (int i = 0; i <= len; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= target; j++) {
                if (j >= stones[i - 1]) {
                    if (dp[i-1][j] || dp[i-1][j - stones[i - 1]]) {
                        dp[i][j] = true;
                    }
                } else {
                    dp[i][j] = dp[i-1][j];
                }

                if (dp[i][j]) {
                    res = Math.max(res, j);
                }
            }
        }
        return Math.abs(res * 2 - sum);
    }

    /**
     * 动规。选取任意i个石头，使重量和尽量接近总重量一半
     * @param stones stones
     * @return res
     */
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int i : stones)
            sum += i;
        int t = sum / 2;
        int[][] f = new int[n + 1][t + 1];//f[i][j] 代表考虑前 i 个物品（数值），凑成总和不超过 j 的最大价值
        //一般背包问题都有重量和价值，在这一题，石子的重量既是重量，又是价值
        //因为我们想让它的重量不超过sum/2，又想让它的重量最大(不超过sum/2)的基础上
        //数组范围就大于sum/2，所以不可能超过sum/2，因此只要让重量最大即可
        for (int i = 1; i <= n; i++) {
            int x = stones[i - 1];
            for (int j = 0; j <= t; j++) {
                f[i][j] = f[i - 1][j];
                if (j >= x)
                    f[i][j] = Math.max(f[i][j], f[i - 1][j - x] + x);
            }
        }
        return Math.abs(sum - f[n][t] - f[n][t]);
    }

    /**
     * 01背包空间优化
     * @param stones stones
     * @return res
     */
    public int lastStoneWeightII2(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int i : stones)
            sum += i;
        int t = sum / 2;
        int[] f = new int[t + 1];
        for (int i = 1; i <= n; i++) {
            int x = stones[i - 1];
            for (int j = t; j >= x; j--) {
                f[j] = Math.max(f[j], f[j - x] + x);
            }
        }
        return Math.abs(sum - f[t] - f[t]);
    }

    public static void main(String[] args) {
        System.out.println(new LastStoneWeightII().lastStoneWeightII4(new int[] {31,26,33,21,40}));
    }
}
