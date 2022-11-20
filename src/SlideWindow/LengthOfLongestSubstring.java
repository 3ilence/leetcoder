package SlideWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : LengthOfLongestSubstring
 * @Author : Silence
 * @Date: 2022/11/19 16:06
 * @Description : 3. 无重复字符的最长子串
 */
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int left = 0, right = 0, res = 0, boundary = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (left <= right && right < s.length()) {
            if (map.getOrDefault(s.charAt(right), -1) < boundary) {
                map.put(s.charAt(right), right);
                right++;
            } else {
                left = map.get(s.charAt(right)) + 1;
                boundary = left;
                map.replace(s.charAt(right), right);
                right++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }
}
