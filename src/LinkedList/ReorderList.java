package LinkedList;

import datastructure.List.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName : ReorderList
 * @Author : Silence
 * @Date: 2022/4/3 21:24
 * @Description : 143. 重排链表
 */
public class ReorderList {

    /**
     * 还可以将空间复杂度优化到O(1)
     * @param head
     */
    public void reorderList(ListNode head) {
        Deque<ListNode> deque = new ArrayDeque<>();
        ListNode Head = new ListNode();
        Head.next = head;
        ListNode fast = Head, slow = Head;
        do {
            fast = fast.next;
            if (fast != null)
                fast = fast.next;
            slow = slow.next;
        } while (fast != null && slow != null);
        fast = slow.next;
        slow.next = null;
        slow = fast;
        while (slow != null) {
            deque.addLast(slow);
            slow = slow.next;
        }
        while (!deque.isEmpty() && head != null) {
            ListNode tmp = deque.removeLast();
            tmp.next = head.next;
            head.next = tmp;
            head = tmp.next;
        }
    }

    public void reorderList2(ListNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        ListNode Head = new ListNode();
        Head.next = head;
        ListNode fast = Head, slow = Head;
        do {
            fast = fast.next;
            if (fast != null)
                fast = fast.next;
            slow = slow.next;
        } while (fast != null && slow != null);
        head = slow;
        fast = slow.next.next;
        slow = slow.next;
        head.next = null;
        ListNode tail = slow;
        while (fast != null) {
            //反转链表
            head = fast.next;//tmp
            fast.next = slow;
            tail.next = head;
            slow = fast;
            fast = head;
        }
        //slow是链表头
        //链表反转
        head = Head.next;
        while (slow != null) {
            ListNode tmp = slow.next;
            slow.next = head.next;
            head.next = slow;
            head = slow.next;
            slow = tmp;
        }
    }
}
