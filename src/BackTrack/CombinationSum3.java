package BackTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : CombinationSum3
 * @Author : Silence
 * @Date: 2022/2/28 20:16
 * @Description : 216. 组合总和 III
 */
public class CombinationSum3 {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> tmp = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        backTrace(1, k, n);
        return res;
    }

    public void backTrace(int start, int k, int n) {
        if (tmp.size() == k && n == 0) {
            res.add(new ArrayList<>(tmp));
        }
        /*if (tmp.size() == k || n < 0 || tmp.size() + 9 - start + 1 < k || (start + 9) * (9 - start + 1) / 2 < n) {
            return;
        }*/
        //上面的虽然剪枝剪得更多，但是会变慢
        if (tmp.size() == k || n < 0 || tmp.size() + 9 - start + 1 < k) {
            return;
        }

        for (int i = start; i <= 9;  i++) {
            tmp.add(i);
            backTrace(i  + 1, k , n - i);
            tmp.remove(tmp.size() - 1);
        }
    }
}
