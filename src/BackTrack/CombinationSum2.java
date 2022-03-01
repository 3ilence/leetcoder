package BackTrack;

import java.util.*;

/**
 * @ClassName : CombinationSum2
 * @Author : Silence
 * @Date: 2022/3/1 13:39
 * @Description : 40. 组合总和 II
 */
public class CombinationSum2 {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> tmp = new ArrayList<>();

    /**
     * 找出目标和为target的所有组合
     * @param candidates 候选者数组
     * @param target 目标和
     * @return 组合组成的列表
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
            if(i > start && candidates[i] == candidates[i-1])
                continue;//去重
            if (target < candidates[i]) {
                break;
            }
            tmp.add(candidates[i]);
            backTrack(candidates, i + 1,target - candidates[i]);//相比CombinationSum改变了这里，因为数组中每个数在每个组合中最多使用一次
            tmp.remove(tmp.size() - 1);
        }
    }
}
