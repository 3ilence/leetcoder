package SlideWindow;

/**
 * @ClassName : MinWindow
 * @Author : Silence
 * @Date: 2022/11/25 17:23
 * @Description : 76. 最小覆盖子串
 */
public class MinWindow {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        int[] initial = new int[58];
        int[] lack = new int[58];
        int left = 0, right = t.length() - 1;
        int flag = 0;//flag表示还缺的字母个数
        char[] arrayS = s.toCharArray();
        for (char c : t.toCharArray()) {
            initial[c - 'A']++;
            lack[c - 'A']++;
            flag++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (initial[arrayS[i] - 'A'] > 0) {
                if (lack[arrayS[i] - 'A'] > 0) {
                    flag--;
                }
                lack[arrayS[i] - 'A']--;
            }
        }
        int[] res = new int[2];
        res[1] = s.length();
        while (left <= right && right < s.length()) {
            if (flag == 0) {
                // 记录res
                if (right - left < res[1] - res[0]) {
                    res[0] = left;
                    res[1] = right;
                }
                //左窗口右移
                if (initial[arrayS[left] - 'A']  > 0) {
                    lack[arrayS[left] - 'A']++;
                    if (lack[arrayS[left] - 'A'] >= 1) {
                        flag++;
                    }
                }
                left++;
            } else {
                //右窗口右移
                right++;
                if (right < s.length() && initial[arrayS[right] - 'A'] > 0) {
                    lack[arrayS[right] - 'A']--;
                    if (lack[arrayS[right] - 'A'] >= 0) {
                        flag--;
                    }
                }
            }
        }
        if (res[1] != s.length()) {
            return s.substring(res[0], res[1] + 1);
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        new MinWindow().minWindow("ADOBECODEBANC", "ABC");
    }
}
