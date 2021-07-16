import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {0,1,0,2,5};
        System.out.println(Solution.isStraight(arr));
    }
    public static int nthUglyNumber(int n) {
        int[] dp = new int[1700];
        dp[0] = 1;
        int pos = 1;
        int a = 0,b = 0,c = 0;
        int nexta = dp[0]*2;
        int nextb = dp[0]*3;
        int nextc = dp[0]*5;
        for (;pos <= n - 1; pos++) {
            dp[pos] = min(nexta,nextb,nextc);
            if (dp[pos] == nexta) {
                a++;
                nexta = dp[a] * 2;
            } else if (dp[pos] == nextb) {
                b++;
                nextb = dp[b] * 3;
            } else {
                c++;
                nextc = dp[c] * 5;
            }
            System.out.println("位置：" + pos + " " + dp[pos]);
        }
        return dp[n-1];
    }
    public static int min(int a,int b,int c) {
        if (a < b) {
            return a < c?a:c;
        } else {
            return b < c?b:c;
        }
    }

}
