package BackTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : Partition
 * @Author : Silence
 * @Date: 2022/3/1 16:10
 * @Description : 131. 分割回文串
 */
public class Partition {
    List<List<String>> res = new ArrayList<>();
    List<String> tmp = new ArrayList<>();

    public List<List<String>> partition(String s) {
        if (s.length() == 1) {
            res.add(new ArrayList<>(){{
                add(s);
            }});
            return res;
        }
        backTrack(s, 0);
        return res;
    }

    public void backTrack(String s, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        int end = start;
        for (; end < s.length(); end++) {
            if (isPalindrome(s.substring(start, end + 1))) {
                tmp.add(s.substring(start, end + 1));
            } else {
                continue;
            }
            backTrack(s, end + 1);
            tmp.remove(tmp.size() - 1);
        }
    }

    /**
     * 判断是否是回文串
     * @param s 字符串
     * @return true or false
     */
    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        int len = s.length();
        if (len == 1 || len == 0) {
            return true;
        }
        int left = 0, right = len - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}
