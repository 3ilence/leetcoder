package DynamicProgramming;

/**
 * @ClassName : longestPalindrome
 * @Author : Silence
 * @Date: 2022/11/20 15:14
 * @Description : 5. 最长回文子串
 */
public class longestPalindrome {

    /**
     * 解法动态规划，记长度为n的子串是否是回文串，如果是的话回文串的长度为f(n)，那么f(n)完全依赖于f(n-2)，不过回文串
     * 不过我的解法的问题在于我用的是闭区间，比如dp[1][2]表示的是s.substring(1,3)，[1][2]依赖于[2][3]，但是[2][3]是不合法的
     * 另外res还是不要用String，因为这样在找的过程中调用了很多次substring，效率不高
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null) {
            return s;
        }
        int length = s.length();
        if (length <= 1) {
            return s;
        }
        int[][] dp = new int[length][length];
        String res = "" + s.charAt(0);
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
        }
        for (int l = 2; l <= length; l++) {
            for (int i = 0; i < length - l + 1; i++) {
                int j = i + l - 1;
                if (l == 2) {
                    dp[i][j] = s.charAt(i) == s.charAt(j) ? 2 : 0;
                } else if (dp[i+1][j-1] == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) ? dp[i+1][j-1] + 2 : 0;
                }
                if (res.length() < dp[i][j]) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    /**
     * 中心扩展法
     * @param s
     * @return
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("badbd"));
    }
}
