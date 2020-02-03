/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class LC148 {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        ListNode res = sortList(head);
        return;
    }

    public static ListNode sortList(ListNode head) {
        //注意这里是递归的终止条件，当head本身为null的时候直接返回null
        //同时如果当head只有一个节点的时候就直接把当前节点return就好了, 如果不return的话，当只剩下一个节点的时候进入死循环
        if (head == null || head.next == null) return head;
        
        ListNode slow = head, fast = head, pre = head;
        
        //1.cut list to 2 parts
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        //将链表切断，否则下次递归的时候fast仍然会一直走到原始链表的尾部
        pre.next = null;
        
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        
        //merge
        return merge(l1, l2);
    }
    
    private static ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        
        if (l2 == null) return l1;
        
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        
        if (l1 != null) {
            curr.next = l1;
        } else if (l2 != null) {
            curr.next = l2;
        }
        
        return dummy.next;
    }
}