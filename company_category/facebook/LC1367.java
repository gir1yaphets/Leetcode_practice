class LC1367 {
    public boolean isSubPath(ListNode head, TreeNode root) {
        return dfs(head, root);
    }
    
    private boolean dfs(ListNode head, TreeNode root) {
        if (head == null) return true;
        if (root == null) return false;
        
        if (head.val == root.val) {
            if (isEqual(head, root)) {
                return true;
            }
        }
        
        return dfs(head, root.left) || dfs(head, root.right);
    }
    
    private boolean isEqual(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        
        if (root == null) {
            return false;
        }
        
        if (head.val != root.val) {
            return false;
        }
        
        return isEqual(head.next, root.left) || isEqual(head.next, root.right);
    }
}