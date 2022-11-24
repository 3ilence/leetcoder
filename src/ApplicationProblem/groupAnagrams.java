package ApplicationProblem;

import java.util.*;

/**
 * @ClassName : groupAnagrams
 * @Author : Silence
 * @Date: 2022/11/24 13:07
 * @Description : 49. 字母异位词分组
 */
public class groupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            String key = cal(s);
            if (map.containsKey(key)) {
                map.get(key).add(s);
            } else {
                map.put(key, new ArrayList<String>(){{
                    add(s);
                }});
            }
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }

    public String cal(String s) {
        int[] flag = new int[26];
        for(char c : s.toCharArray()) {
            flag[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (flag[i] != 0) {
                sb.append('a' + i).append(flag[i] + "");
            }
        }
        return sb.toString();
    }
}
