package DoublePointer.SlideWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : FindLongestSubString
 * @Author : Silence
 * @Date: 2022/3/16 21:50
 * @Description : 159.字符串的最多包含两种字符的最长子串
 */
public class FindLongestSubString {

    /**
     * 滑动窗口
     * @param s s
     * @return 最长子串
     */
    public static String findLongest(String s) {
        if (s == null || s.length() <= 2) {
            return s;
        }
        int[] res = new int[2];
        int left = 0, right = 0, max = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (right < s.length()) {
            if (max >= s.length() - left) {
                break;
            }
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) + 1);
            if (map.size() > 2) {
                if (max < right - left) {
                    res[0] = left;
                    res[1] = right;
                    max = right - left;
                }
                while (map.size() > 2) {
                    map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                    if (map.get(s.charAt(left)) == 0) {
                        map.remove(s.charAt(left));
                    }
                    left++;
                }
            }
            right++;
        }
        if (max < right - left) {
            return s.substring(left, right);
        }
        return s.substring(res[0], res[1]);
    }
}
