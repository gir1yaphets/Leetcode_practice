/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class LC24 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        
        while (curr.next != null && curr.next.next != null) {
            ListNode first = curr.next, second = curr.next.next;
            //让两个节点交换
            first.next = second.next;
            second.next = first;
            
            //用curr将所有的节点串起来
            curr.next = second;
            curr.next.next = first;
            
            curr = curr.next.next;
            
        }
        
        return dummy.next;
    }
}