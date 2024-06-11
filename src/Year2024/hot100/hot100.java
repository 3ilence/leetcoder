package Year2024.hot100;

import datastructure.List.ListNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

/**
 * @Author : silence.zhu
 * @Date: 2024/6/12 0:44
 * @Description :
 */
public class hot100 {

    /**
     * NO.11 盛水最多的容器
     * 解法：双指针
     */
    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int len = height.length;
        // 以[0, len-1]作为参考，要想在长度变短的条件下，面积变大，一定是短板要变长
        int i = 0, j  = len - 1;
        int max = Math.min(height[i], height[j]) * (j - i);
        while (i < j) {
            if (height[i] <= height[j]) {
                int tmp = i;
                while (++tmp < len && height[i] > height[tmp]);
                if (tmp == len) {
                    return max;
                }
                i = tmp;
                max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            } else {
                int tmp = j;
                while (--tmp >= 0 && height[j] > height[tmp]);
                if (tmp < 0) {
                    return max;
                }
                j = tmp;
                max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return max;
    }

    /**
     * NO.14 最长公共前缀
     * 解法：暴力扫描
     * 最优解法：分治 todo
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null) {
            return "";
        }
        if (strs.length < 2) {
            return strs[0];
        }
        StringBuilder sb = new StringBuilder();
        int i = -1;
        while(i + 1 < strs[0].length()) {
            i++;
            sb.append(strs[0].charAt(i));
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= i || sb.charAt(i) != strs[j].charAt(i)) {
                    return sb.substring(0, sb.length() - 1);
                }
            }
        }
        return sb.toString();
    }

    /**
     * NO.17 电话号码的字母组合
     * 解法：深搜/递归，但是因为这里不关注字母的顺序，也可以用迭代完成
     */
    public List<String> letterCombinations(String digits) {
        char[][] dic = {{}, {}, {'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        List<String> res = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return res;
        }
        StringBuilder sb = new StringBuilder();
        recursion(digits, 0, sb, res, dic);
        return res;
    }

    public void recursion(String str, int index, StringBuilder sb, List<String> res, char[][] dic) {
        if (index >= str.length()) {
            if (!sb.isEmpty()) {
                res.add(sb.toString());
            }
            return;
        }
        if (dic[str.charAt(index) - '0'].length > 0) {
            for (char c : dic[str.charAt(index) - '0']) {
                sb.append(c);
                recursion(str, index + 1, sb, res, dic);
                sb.deleteCharAt(sb.length() - 1);
            }
        } else {
            recursion(str, index + 1, sb, res, dic);
        }
    }

    /**
     * NO.19 删除链表的倒数第n个节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 参数不合理
        if (n < 1 || Objects.isNull(head)) {
            return null;
        }
        ListNode cur = head;
        for (int i = 1; i < n; i++) {
            // 参数不合理
            if (Objects.isNull(cur)) {
                return null;
            }
            cur = cur.next;
        }
        // 链表长度为5，倒数第5个就是第一个
        if (Objects.isNull(cur.next)) {
            return head.next;
        }
        ListNode tmp = head;
        ListNode pre = null;
        while (cur.next != null) {
            pre = tmp;
            tmp = tmp.next;
            cur = cur.next;
        }
        pre.next = pre.next.next;
        return head;
    }


    /**
     * NO20. 有效括号
     */
    public boolean isValid(String s) {
        if (Objects.isNull(s)) {
            return false;
        }
        if (s.length() <= 1) {
            return false;
        }
        if (s.length() % 2 != 0) {
            return false;
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty() && judgeValid(stack.peek(), s.charAt(i))) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }

    public boolean judgeValid(char a, char b) {
        if (a == '(' && b == ')') {
            return true;
        } else if (a == '[' && b == ']') {
            return true;
        } else if (a == '{' && b == '}') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * NO.21 合并两个有序链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head;
        if (l1.val < l2.val) {
            head = l1;
            l1 = l1.next;
        } else {
            head = l2;
            l2 = l2.next;
        }

        while (l1 != null || l2 != null) {
            if (l1 == null) {
                head.next = l2;
                l2 = l2.next;
            } else if (l2 == null) {
                head.next = l1;
                l1 = l1.next;
            } else {
                if (l1.val < l2.val) {
                    head.next = l1;
                    l1 = l1.next;
                } else {
                    head.next = l2;
                    l2 = l2.next;
                }
            }

            head = head.next;
        }
        return head;
    }

    /**
     * NO.22 生成括号
     */
    public List<String> generate(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        recursion(n, 0, 0, sb, res);
        return res;
    }

    /**
     * 只要保证左括号数大于等于右括号数，那么这个字符串是有效的括号
     */
    public void recursion(int n, int left, int right, StringBuilder sb, List<String> res) {
        if (n <= 0) {
            return;
        }
        if (left == n) {
            while (right++ < n) {
                sb.append(')');
            }
            res.add(sb.toString());
            return;
        }

        for (int i = 0; i <= right - left; i++) {
            for (int j = 0; j < i; j++) {
                sb.append(')');
                right++;
            }
            sb.append('(');
            recursion(n, left + 1, right, sb, res);
            sb.deleteCharAt(sb.length() - 1);

            for (int j = 0; j < i; j++) {
                sb.deleteCharAt(sb.length() - 1);
                right--;
            }
        }

    }

    /**
     * NO.32 最长有效括号
     */
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        int res = 0;
        int l = 0, r = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                sb.append(s.charAt(i));
                l++;
            } else if(s.charAt(i) == ')') {
                sb.append(s.charAt(i));
                r++;
                if (r == l) {
                    if (isValid(sb.toString())) {
                        res = Math.max(res, sb.length() / 2);
                    }
                } else if (r > l) {
                    l = 0;
                    r = 0;
                    sb = new StringBuilder();
                }
            } else {
                throw new RuntimeException();
            }
        }
        return res;
    }

    /**
     * NO.33 搜索旋转排序数组
     */
    public int search(int[] nums, int target) {
        /*if (nums == null || nums.length < 1) {
            throw new RuntimeException();
        }
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                if (target > ) {

                }
            } else {
                l = mid + 1;
            }
        }*/
        return -1;
    }
}
