/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC1325 {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        return helper(root, target);
    }
    
    private TreeNode helper(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        
        root.left = helper(root.left, target);
        root.right = helper(root.right, target);
        
        if (root.val == target && root.left == null && root.right == null) {
            return null;
        }
        
        return root;
    }
}