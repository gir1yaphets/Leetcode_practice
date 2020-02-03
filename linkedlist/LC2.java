class LC2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = helper(l1, l2, 0);
        return head;
    }
    
    private ListNode helper(ListNode l1, ListNode l2, int inc) {
        ListNode curr;
        int sum = 0;
        
        if (l1 == null && l2 == null) {
            return inc == 0 ? null : new ListNode(inc);
        }
        
        if (l1 == null) {
            sum = l2.val + inc;
            curr = new ListNode(sum >= 10 ? sum % 10 : sum);
            curr.next = helper(l1, l2.next, sum < 10 ? 0 : 1);
        } else if (l2 == null) {
            sum = l1.val + inc;
            curr = new ListNode(sum >= 10 ? sum % 10 : sum);
            curr.next = helper(l1.next, l2, sum < 10 ? 0 : 1);
        } else {
            sum = l1.val + l2.val + inc;
            curr = new ListNode(sum >= 10 ? sum % 10 : sum);
            curr.next = helper(l1.next, l2.next, sum < 10 ? 0 : 1);
        }
        
        return curr;
    }
}