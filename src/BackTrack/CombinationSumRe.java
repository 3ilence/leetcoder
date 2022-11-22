package BackTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName : CombinationSumRe
 * @Author : Silence
 * @Date: 2022/11/22 21:42
 * @Description : 39. 组合总和
 */
public class CombinationSumRe {
    List<List<Integer>> res;

    /**
     * 没有之前的写法快，试验了一下，发现主要是因为没有把list也当作全局变量
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        Arrays.sort(candidates);
        List<Integer> list = new ArrayList<>();
        dfs(candidates, 0, target, list);
        return res;
    }

    public void dfs(int[] candidates, int index, int rest, List<Integer> list) {
        if (rest == 0) {
            res.add(new ArrayList(list));
            return;
        }

        if (index >= candidates.length) {
            return;
        }

        for (int i = 0;;i++) {
            if (rest < i * candidates[index]) {
                return;
            }
            for (int j = 0; j < i; j++) {
                list.add(candidates[index]);
            }
            dfs(candidates, index + 1, rest - i * candidates[index], list);
            for (int j = 0; j < i; j++) {
                list.remove(list.size() - 1);
            }
        }
    }
}
