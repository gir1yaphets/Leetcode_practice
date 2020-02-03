class Solution {
    private int count = 0;
    
    public int countUnivalSubtrees(TreeNode root) {
        helper(root);
        return count;
    }
    
    private boolean helper(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        //如果是叶子节点的话该节点一定是uni value, count++
        if (root.left == null && root.right == null) {
            count += 1;
            return true;
        }
        
        boolean left = helper(root.left);
        boolean right = helper(root.right);
        
        //如果左子树或者右子树并不是uni value的，那么当前节点一定不是，直接返回false
        if (!left || !right) {
            return false;
        }
        
        //当前节点和左右节点值不相同的时候不是uni value
        if (root.left != null && root.left.val != root.val || root.right != null && root.right.val != root.val) {
            return false;
        }
        
        count += 1;
        return true;
    }
}