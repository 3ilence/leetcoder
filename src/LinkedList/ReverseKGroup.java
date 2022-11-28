package LinkedList;

import datastructure.List.ListNode;

/**
 * @ClassName : ReverseKGroup
 * @Author : Silence
 * @Date: 2022/11/28 20:42
 * @Description : 25. K 个一组翻转链表
 */
public class ReverseKGroup {

    /**
     * 有几个点：第一点，如何每次都将链表节点移到表头；第二点，递归反转的时候需要注意的是，本次递归翻转完成之后还需要将原head的pre节点的next重定向，因为原head会变为现tail
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        ListNode Head = new ListNode();
        Head.next = head;
        reverse(Head.next, k, Head);
        return Head.next;
    }

    public void reverse(ListNode head, int k, ListNode pre) {
        if (head == null || k == 1) {
            return;
        }
        ListNode Head = new ListNode(), tail = head;
        Head.next = head;
        for (int i = 0; i < k; i++) {
            if (head == null) {
                return;
            }
            head = head.next;
        }
        ListNode cur = Head.next.next;
        head = Head.next;
        for (int i = 0; i < k - 1; i++) {
            if (cur == null) {
                return;
            }
            //每次将节点移到第一位
            ListNode tmp = cur.next;
            cur.next = head;
            for (int j = 0; j < i; j++) {
                head = head.next;
            }//把这里的for循环注释掉，并且下一行的head换成tail，tail永远是固定的，beat 1% -> beat 100%
            head.next = tmp;
            head = cur;
            cur = tmp;
        }
        pre.next = head;
        reverse(cur, k, tail);
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);
//        head.next.next.next.next.next = new ListNode(6);
        new ReverseKGroup().reverseKGroup(head, 2);
    }
}
