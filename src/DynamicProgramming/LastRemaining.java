package DynamicProgramming;

/**
 * @ClassName : LastRamaining
 * @Author : Silence
 * @Date: 2022/3/13 16:46
 * @Description : 剑指 Offer 62. 圆圈中最后剩下的数字
 */
public class LastRemaining {

    /**
     * 动态规划
     * @param n n
     * @param m m
     * @return res
     */
    public int lastRemaining(int n, int m) {
        int x = 0;
        for (int i = 2; i <= n; i++) {
            x = (x + m) % i;
        }
        return x;
    }
}
