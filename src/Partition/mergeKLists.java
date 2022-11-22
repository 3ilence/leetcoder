package Partition;

import datastructure.List.ListNode;

import java.util.Arrays;

/**
 * @ClassName : mergeKLists
 * @Author : Silence
 * @Date: 2022/11/22 11:00
 * @Description : 23. 合并K个升序链表
 */
public class mergeKLists {

    /**
     * 归并，本质是分治
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int len = lists.length;
        if (len == 1) {
            return lists[0];
        } else if (len == 2) {
            return merge(lists[0], lists[1]);
        } else {
            return merge(mergeKLists(Arrays.copyOfRange(lists, 0, len / 2)),
                    mergeKLists(Arrays.copyOfRange(lists, len/2, len)));
        }
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(), res = head;
        int constant = 1000000;
        while (l1 != null || l2 != null) {
            int v1 = l1 != null ? l1.val : constant;
            int v2 = l2 != null ? l2.val : constant;
            if (v1 < v2) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }
        return res.next;
    }

    /**
     * 看到一种对我来讲不容易想出的做法，不过这样递归层次太多应该效率不好
     * @param l1
     * @param l2
     * @return
     */
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }
}
