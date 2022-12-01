package MonotonicStack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName : NextGreaterElement
 * @Author : Silence
 * @Date: 2022/3/20 14:46
 * @Description : 496. 下一个更大元素 I
 */
public class NextGreaterElement {

    /**
     * 2022.12.1
     * 看了题解还可以倒序遍历来做，不过大同小异。找下一个最大元素一定用单调栈
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        Deque<Integer> deque = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums2[i]) {
                map.put(deque.removeLast(), nums2[i]);
            }
            deque.addLast(nums2[i]);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }

    /**
     * 2022.3.20
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();//反向遍历，递减栈
        for (int i = nums2.length - 1; i >= 0 ; i--) {
            if (stack.isEmpty() || stack.peek() > nums2[i]) {
                if (stack.isEmpty()) {
                    map.put(nums2[i], -1);
                } else {
                    map.put(nums2[i], stack.peek());
                }
                stack.push(nums2[i]);
            } else {
                while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    map.put(nums2[i], -1);
                } else {
                    map.put(nums2[i], stack.peek());
                }
                stack.push(nums2[i]);
            }
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i : new NextGreaterElement().nextGreaterElement(new int[] {4,1,2}, new int[] {1,3,4,2})) {
            System.out.print(i + " ");
        }
    }
}
