package BackTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName : CombinationSum
 * @Author : Silence
 * @Date: 2022/3/1 13:24
 * @Description : 39. 组合总和
 */
public class CombinationSum {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> tmp = new ArrayList<>();

    /**
     * 找出目标和为target的所有组合
     * @param candidates 候选者数组
     * @param target 目标和
     * @return 组合组成的列表
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);//排序，方便去重
        backTrack(candidates, 0, target);
        return res;
    }

    /**
     * 回溯
     * @param candidates 候选者数组
     * @param start candidates下标起点
     * @param target 目标和
     */
    public void backTrack(int[] candidates, int start, int target) {
        if (target == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i =  start; i < candidates.length; i++) {
            if (target < candidates[i]) {
                break;
            }
            tmp.add(candidates[i]);
            backTrack(candidates, i,target - candidates[i]);
            tmp.remove(tmp.size() - 1);
        }
    }
}
