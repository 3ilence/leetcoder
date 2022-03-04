package GreedyAlgorithm;

/**
 * @ClassName : WiggleMaxLength
 * @Author : Silence
 * @Date: 2022/3/4 18:46
 * @Description : 376. 摆动序列
 */
public class WiggleMaxLength {
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int[] up = new int[n];
        int[] down = new int[n];
        up[0] = down[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                up[i] = up[i - 1];
                down[i] = Math.max(up[i - 1] + 1, down[i - 1]);
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[n - 1], down[n - 1]);
    }

    public static void main(String[] args) {
        System.out.println(new WiggleMaxLength().wiggleMaxLength(new int[] {1,2,3,-2}));

    }
}
