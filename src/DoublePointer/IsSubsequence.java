package DoublePointer;

/**
 * @ClassName : IsSubsequence
 * @Author : Silence
 * @Date: 2022/3/22 10:47
 * @Description : 392. 判断子序列
 */
public class IsSubsequence {

    // <https://leetcode-cn.com/problems/is-subsequence/>
    /**
     * 初始化两个指针 i和 j，分别指向 s和 t的初始位置。每次贪心地匹配，匹配成功则 i和 j同时右移；匹配失败则 j右移，i不变，尝试用 t的下一个字符匹配 s。
     * @param s s
     * @param t t
     * @return s是否是t的子序列
     */
    public boolean isSubsequence(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }
}
