package BinarySearch;

/**
 * @ClassName : search
 * @Author : Silence
 * @Date: 2022/11/22 15:56
 * @Description : 33. 搜索旋转排序数组
 */
public class search {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid;
        //简而言之，nums[mid]需要接近target
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] < nums[0]) {
                if (nums[mid] < target) {
                    if (target > nums[0]) {
                        right = mid - 1;
                    } else if (target < nums[0]) {
                        left = mid+1;
                    } else {
                        return 0;
                    }

                } else if (nums[mid] > target) {
                        right = mid - 1;
                } else {
                    return mid;
                }
            } else if (nums[mid] >= nums[0]){
                if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    if (target > nums[0]) {
                        right = mid - 1;
                    } else if (target < nums[0]){
                        left = mid + 1;
                    } else {
                        return 0;
                    }
                } else {
                    return mid;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        new search().search(new int[]{1}, 0);
    }
}
