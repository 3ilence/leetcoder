package DynamicProgramming;

/**
 * @ClassName : LastStoneWeightII
 * @Author : Silence
 * @Date: 2022/3/10 10:41
 * @Description : 1049. 最后一块石头的重量 II
 */
public class LastStoneWeightII {

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
        System.out.println(new LastStoneWeightII().lastStoneWeightII(new int[] {2,7,4,1,8,1}));
    }
}
