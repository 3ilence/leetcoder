package SlideWindow;

/**
 * @ClassName : FindLength
 * @Author : Silence
 * @Date: 2022/3/21 19:06
 * @Description : 718. 最长重复子数组
 */
public class FindLength {

    /**
     * 滑窗解法，根据重复子数组的位置，将两个数组错位
     * 枚举 A 和 B 所有的对齐方式。对齐的方式有两类：第一类为 A 不变，B 的首元素与 A 中的某个元素对齐；
     * 第二类为 B 不变，A 的首元素与 B 中的某个元素对齐
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            res = Math.max(res, head(nums1, i, nums2, 0));
            res = Math.max(res, tail(nums1, i, nums2, nums2.length - 1));
        }
        return res;
    }

    public int head(int[] nums1, int start1, int[] nums2, int start2) {
        int max = 0;
        int tmp = 0;
        while (start1 < nums1.length && start2 < nums2.length) {
            if (nums1[start1] == nums2[start2]) {
                tmp++;
            } else {
                tmp = 0;
            }
            max = Math.max(max, tmp);
            start1++;
            start2++;
        }
        return max;
    }

    public int tail(int[] nums1, int end1, int[] nums2, int end2) {
        int max = 0, tmp = 0;
        while (end1 >= 0 && end2 >= 0) {
            if (nums1[end1] == nums2[end2]) {
                tmp++;
            } else {
                tmp = 0;
            }
            max = Math.max(max, tmp);
            end1--;
            end2--;
        }
        return max;
    }
}
