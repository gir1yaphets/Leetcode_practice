class LC1145 {
    private int left, right = 0;
    
    /**
     * 蓝色可以取值的三个区域：
     * 1. 红色节点的父节点控制的除红色子树以外的所有区域
     * 2. 红色节点的左子树
     * 3. 红色节点的右子树
     */
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        helper(root, x);
        int p = n - (left + right + 1);
        int max = Math.max(Math.max(p, left), right);
        return max > n / 2;
    }
    
    private int helper(TreeNode root, int x) {
        if (root == null) return 0;
        
        int l = helper(root.left, x);
        int r = helper(root.right, x);
        
        if (root.val == x) {
            left = l;
            right = r;
        }
        
        return l + r + 1;
    }    
}