import java.util.*;

/**
 * @ClassName : demo
 * @Author : Silence
 * @Date: 2021/8/6 7:43
 * @Description :
 */
class demo {
    List<Integer> combine = new LinkedList<>();
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        Arrays.sort(candidates);
        backTrack(candidates, target, 0);
        return res;
    }

    int backTrack(int[] candidates, int target, int index) {
        if (target == 0) {
            res.add(new LinkedList<>(combine));
            return 1;
        }
        int sum = 0;
        if (index == candidates.length || target < candidates[index] ) {
            return 0;
        } else {
            //有两种选择，选index或者不选
            combine.add(candidates[index]);
           sum += backTrack(candidates, target - candidates[index], index);
           combine.remove(combine.size() - 1);
           sum += backTrack(candidates, target, index + 1);
        }
        return sum;
    }

    public static void main(String[] args) {
        for (List l : new demo().combinationSum(new int[]{2, 3, 5}, 8)) {
            for (Object i : l) {
                System.out.print((Integer) i + " ");
            }
            System.out.println();
        }
    }
}