package BackTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : PermuteRe
 * @Author : Silence
 * @Date: 2022/11/23 10:40
 * @Description : 46.全排列
 */
public class PermuteRe {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    boolean[] flag = new boolean[3];

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) {
            return res;
        }
        if (nums.length == 1) {
            list.add(nums[0]);
            res.add(list);
            return res;
        }
        BackTrack(nums, 3);
        return res;
    }

    public void BackTrack(int[] nums, int rest) {
        if (rest == 0) {
            res.add(new ArrayList(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (flag[i]) {
                continue;
            }
            list.add(nums[i]);
            flag[i] = true;
            BackTrack(nums, rest - 1);
            flag[i] = false;
            list.remove(list.size() - 1);
        }
        return;
    }
}
