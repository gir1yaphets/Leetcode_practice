/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/**
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 */
class LC92 {
    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        reverseBetween(head, 2, 4);
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;

            ListNode curr = dummy;
            int i = 0;
            ListNode revHead = null;
            ListNode pre = null;

            while (i < m - 1) {
                curr = curr.next;
                i++;
            }

            //出while循环以后curr指向反转的前一个节点，first是反转的头节点
            revHead = curr.next;
            ListNode p = revHead;

            ListNode temp = null;
            while (i <= n - 1) {
                temp = p.next;
                p.next = pre;
                pre = p;
                p = temp;

                i++;
            }

            //出while循环以后p指向反转部分的下一个节点，pre是反转的尾节点
            if (revHead != null) {
                revHead.next = p;
            }

            curr.next = pre;

            return dummy.next;
    }
}