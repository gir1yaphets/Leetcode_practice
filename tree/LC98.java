//往左边走的时候将当前节点的值作为最大值传下去，往右边走的时候将当前节点的值作为最小值传下去 子树的值不能小于最小也不能大于最大

class LC98 {
    public boolean isValidBST(TreeNode root) {
        return helper(root, null, null); 
    }
    
    private boolean helper(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        
        if (min != null && root.val <= min || max != null && root.val >= max) {
            return false;
        }
        
        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }
}