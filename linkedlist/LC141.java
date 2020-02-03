public class LC141 {
    public boolean hasCycle_Mine(ListNode head) {
        if (head == null) return false;
        
        ListNode curr = head;
        
        while (curr != null) {
            if (curr.next != null && curr.next.val == Integer.MAX_VALUE) {
                return true;
            }
            
            curr.val = Integer.MAX_VALUE;
            curr = curr.next;
        }
        
        return false;
    }

    /**
     * 双指针：如果存在环，则快慢指针一定相遇，e.g 当存在环的时候快指针落后于慢指针一步的时候，下一步就相遇
     * 若落后两步，则下一步为落后一步
     */
    public boolean hasCycle_TP(ListNode head) {
        if (head == null || head.next == null) return false;
        
        ListNode slow = head, fast = head;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            
            if (fast == slow) {
                return true;
            }
        }
        
        return false;
    }
}