class LC111 {
    private int min = Integer.MAX_VALUE;
    
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        helper(root, 1);//root的深度是1，不是0
        return min;
    }
    
    private void helper(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        
        if (root.left == null && root.right == null) {
            min = Math.min(min, level);
            return;
        }
        
        helper(root.left, level + 1);
        helper(root.right, level + 1);
    }
}