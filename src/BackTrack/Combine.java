package BackTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : Combine
 * @Author : Silence
 * @Date: 2022/2/28 20:00
 * @Description : 77.组合
 */
public class Combine {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        for (int i = 1; i <= n; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(i);
            backTrace(list, i + 1, n, k);
            list.remove(list.size() - 1);
        }
        return res;
    }

    public void backTrace(List<Integer> list, int start, int n, int k) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (list.size() + n - start + 1 < k) {
            return;//剪枝
        }
        for (int i = start; i <= n; i++ ) {
            list.add(i);
            backTrace(list, i + 1, n, k);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        new Combine().combine(4, 2);
    }
}
