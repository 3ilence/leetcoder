package BackTrack;

import java.util.*;

/**
 * @ClassName : FindSubsequences
 * @Author : Silence
 * @Date: 2022/3/1 20:19
 * @Description : 491. 递增子序列
 */
public class FindSubsequences {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> tmp = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backTrack(nums, 0);
        return res;
    }

    public void backTrack(int[] nums, int start) {
        if (tmp.size() >= 2) {
            res.add(new ArrayList<>(tmp));
        }
        Set<Integer> set = new HashSet<>();

        for (int i = start; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                continue;
            }//在本层用过的数不能再用，即去重
            set.add(nums[i]);

            if (tmp.isEmpty() || nums[i] >= tmp.get(tmp.size() - 1)) {
                tmp.add(nums[i]);
            } else {
                continue;
            }

            backTrack(nums, i + 1);
            tmp.remove(tmp.size() - 1);
        }
    }
}
