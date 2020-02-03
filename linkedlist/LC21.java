class LC21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        return helper(l1, l2);
    }
    
    private ListNode helper(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        
        if (l2 == null) return l1;
        
        //将当前值小的节点return回去
        if (l1.val > l2.val) {
            l2.next = helper(l1, l2.next);
            return l2;
        } else {
            l1.next = helper(l1.next, l2);
            return l1;
        }
    }
}