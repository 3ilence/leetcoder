package BagQuestion;

import java.util.Scanner;

public class BagQuestion {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        //n表示物品的个数
        int m = in.nextInt();
        //m表示背包的容量
        int[] V = new int[n];
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
        //K[i][j]为最有价值的总集,可以把前i个物品放进能够承重为j的背包的子集分两个类别
        for (int i = 0; i <= n; i++)
        {
            for (int j = 0; j <= m; j++)
            {
                if (i==0 || j==0)
                    K[i][j] = 0;
                else if (W[i-1] <= j)
                    K[i][j] = Math.max(V[i-1] + K[i-1][j-W[i-1]], K[i-1][j]);
                else
                    K[i][j] = K[i-1][j];
            }
        }
        return K[n][m];
    }

    static int knapSack2(int m, int[] W, int[] V, int n) {
        int[] res = new int[m+1];
        for (int i = 0; i < n; i++) {
            for (int j = m; j > W[i]; j--) {
                res[j] = Math.max(res[j - W[i]] + V[i], res[j - W[i]]);
            }
        }
        return res[m];
    }
}
