package BackTrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName : LetterCombinations
 * @Author : Silence
 * @Date: 2022/3/1 13:00
 * @Description : 17. 电话号码的字母组合
 */
public class LetterCombinations {

    Map<Character, String> map = new HashMap<>(){{
        put('2', "abc");put('3', "def");put('4', "ghi");put('5', "jkl");
        put('6', "mno");put('7', "pqrs");put('8', "tuv");put('9', "wxyz");
    }};//其实用数组就行了：["","","abc", ...]
    StringBuilder sb = new StringBuilder();
    List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return res;
        }//别忘记了digits为空的情况
        backTrack(digits);
        return res;
    }

    public void backTrack(String digits) {
        if (digits.length() == 0) {
            res.add(sb.toString());
        }

        String str = map.get(digits.charAt(0));
        for (int i = 0; i < str.length(); i++) {
            sb.append(str.charAt(i));
            backTrack(digits.substring(1));
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
