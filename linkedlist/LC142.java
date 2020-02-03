/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class LC142 {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        boolean hasCycle = false;
        
        //先用快慢指针找到相遇点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        
        if (!hasCycle) return null;
        
        ListNode starter = head;
        
        //慢指针从相遇点开始走，另一个新指针从head开始走，相遇点就是环的起始点
        while (starter != slow) {
            starter = starter.next;
            slow = slow.next;
        }
        
        return starter;
    }
}