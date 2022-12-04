package KMP;

/**
 * @ClassName : strStr
 * @Author : Silence
 * @Date: 2022/12/4 21:02
 * @Description : 28. 找出字符串中第一个匹配项的下标
 */
public class StrStr {

    /**
     * KMP，其实看了讲解后知道怎么做还是不难，但是代码实现起来想要简洁颇有难度
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (haystack.isEmpty()) return 0;

        // 分别读取原串和匹配串的长度
        int n = haystack.length(), m = needle.length();
        // 原串和匹配串前面都加空格，使其下标从 1 开始
        haystack = " " + haystack;
        needle = " " + needle;

        char[] s = haystack.toCharArray();
        char[] p = needle.toCharArray();

        // 构建 next 数组，数组长度为匹配串的长度（next 数组是和匹配串相关的）
        int[] next = new int[m + 1];
        // 构造过程 i = 2，j = 0 开始，i 小于等于匹配串长度 【构造 i 从 2 开始】
        for (int i = 2, j = 0; i <= m; i++) {
            // 匹配不成功的话，j = next(j)
            while (j > 0 && p[i] != p[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++
            if (p[i] == p[j + 1]) j++;
            // 更新 next[i]，结束本次循环，i++
            next[i] = j;
        }

        // 我觉得这段匹配的代码也很精妙，j表示目前已匹配了的串长度，i表示正在比较的下标，每次循环比较s[i] == p[1 + j]
        // 如果匹配失败，就需要让j根据next数组回退
        for (int i = 1, j = 0; i <= n; i++) {
            // 匹配不成功 j = next(j)
            while (j > 0 && s[i] != p[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++，结束本次循环后 i++
            if (s[i] == p[j + 1]) j++;
            // 整一段匹配成功，直接返回下标
            if (j == m) return i - m;
        }
        return -1;
    }

    public static void main(String[] args) {
        new StrStr().strStr("sadbutsad", "sad");
    }
}
