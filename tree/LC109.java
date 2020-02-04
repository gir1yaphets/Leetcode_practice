/**
 * T: O(nlogn) : 找中点总共需要logn次 每次的时间 n/2 + 2 * n/4 + 4 * n/8...
 * S: O(logn)
 */
class LC109 {
    public TreeNode sortedListToBST(ListNode head) {
        return helper(head, null);
    }
    
    private TreeNode helper(ListNode start, ListNode end) {
        if (start == end) return null;
        
        ListNode slow = start, fast = start;
        
        while (fast != end && fast.next != end) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        TreeNode root = new TreeNode(slow.val);
        root.left = helper(start, slow);
        //这里start要指向slow.next, 否则当只有两个节点的时候fast和slow都不会移动，那么start永远不能到达end，导致stack over flow.
        root.right = helper(slow.next, end);
        
        return root;
    }
}