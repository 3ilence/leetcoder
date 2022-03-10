package DynamicProgramming;

import java.util.Scanner;

public class BagQuestion {

    /**
     * 01背包通用解法，二维状态数组
     * @param m 背包容量
     * @param W 重量数组
     * @param V 价值数组
     * @param n 物品个数
     * @return 能获得的最大价值
     */
    static int knapSack(int m, int[] W, int[] V, int n)
    {
        int[][] K = new int[n+1][m+1];
        //K[i][j]为最有价值的总集,可以把前i个物品放进还能够承重为j的背包的最大价值
        //注意这里的j是指在放入了前面物品的情况下还剩的容量
        for (int i = 0; i <= n; i++)
        {
            for (int j = 0; j <= m; j++)
            {
                if (i==0 || j==0)
                    K[i][j] = 0;
                //如果背包总容量j不小于第i个物品的占用空间，那么就可以考虑把这个物品加入到背包中
                else if (W[i-1] <= j)
                    K[i][j] = Math.max(V[i-1] + K[i-1][j-W[i-1]], K[i-1][j]);
                //如果容量不够的话，答案和把前i-1个物品放入背包的价值最值相同
                else
                    K[i][j] = K[i-1][j];
            }
        }
        return K[n][m];
    }

    /**
     * 01背包通用解法，一维状态数组(状态压缩后)
     * @param m 背包容量
     * @param W 重量数组
     * @param V 价值数组
     * @param n 物品个数
     * @return 能获得的最大价值
     */
    static int knapSack2(int m, int[] W, int[] V, int n) {
        int[] res = new int[m+1];//res[i]表示容量为i的背包用来装这些物品，能获得的最大价值
        //第一层循环遍历物品
        for (int i = 0; i < n; i++) {
            //第二层循环遍历背包容量，res[j]中的j表示还剩j的容量，这里是倒序遍历
            for (int j = m; j > W[i]; j--) {
                res[j] = Math.max(res[j - W[i]] + V[i], res[j]);
            }
        }
        return res[m];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //n表示物品的个数
        int n = in.nextInt();
        //m表示背包的容量
        int m = in.nextInt();
        //V表示价值
        int[] V = new int[n];
        //W表示重量
        int[] W = new int[n];
        for (int i = 0; i < n; i++) {
            W[i] = in.nextInt();
            V[i] = in.nextInt();
        }
        System.out.println(knapSack(m,W,V,n));
    }
}
