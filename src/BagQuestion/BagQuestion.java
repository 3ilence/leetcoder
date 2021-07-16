package BagQuestion;

import java.util.Scanner;

public class BagQuestion {
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
                //如果剩余容量j不小于第i个物品的占用空间，那么就可以考虑把这个物品加入到背包中
                else if (W[i-1] <= j)
                    K[i][j] = Math.max(V[i-1] + K[i-1][j-W[i-1]], K[i-1][j]);
                //如果容量不够的话，答案和把前i-1个物品放入背包的价值最值相同
                else
                    K[i][j] = K[i-1][j];
            }
        }
        return K[n][m];
    }

    static int knapSack2(int m, int[] W, int[] V, int n) {
        int[] res = new int[m+1];
        for (int i = 0; i < n; i++) {
            //一开始容量为m，如果j
            for (int j = m; j > W[i]; j--) {
                //
                res[j] = Math.max(res[j - W[i]] + V[i], res[j]);
            }
        }
        return res[m];
    }
}
