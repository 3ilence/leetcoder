package BinarySearch;

/**
 * @ClassName : SearchRotateArray
 * @Author : Silence
 * @Date: 2022/3/14 10:02
 * @Description : 33.搜索旋转排序数组
 */
public class SearchRotateArray {

    /**
     * 二分
     * @param nums nums
     * @param target target
     * @return 找到的下标
     */
    public int search(int[] nums, int target) {
        int len = nums.length - 1;
        int left = 0, right = len, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                //可以对下面的if-else进行逻辑优化
                if (nums[mid] >= nums[left] ) {
                    left = mid + 1;
                } else {
                    if (nums[left] <= target) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            } else {
                if (nums[mid] < nums[left]) {
                    right = mid - 1;
                } else {
                    if (target < nums[left]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 二分，逻辑优化
     * @param nums nums
     * @param target target
     * @return 找到的下标
     */
    public int search2(int[] nums, int target) {
        int len = nums.length - 1;
        int left = 0, right = len, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                if (nums[mid] < nums[left] && nums[left] <= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] >= nums[left] && target < nums[left]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new SearchRotateArray().search(new int[] {1,3}, 2));
    }
}
