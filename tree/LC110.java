/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC110 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        
        int left = helper(root.left);
        int right = helper(root.right);
        
        if (Math.abs(left - right) > 1) return false;
        
        return isBalanced(root.left) && isBalanced(root.right);
    }
    
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return Math.max(helper(root.left), helper(root.right)) + 1;
    }
}