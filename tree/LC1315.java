/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC1315 {
    private int sum = 0;
    
    public int sumEvenGrandparent(TreeNode root) {
        helper(root, null, null);
        return sum;
    }
    
    private void helper(TreeNode root, TreeNode parent, TreeNode gp) {
        if (root == null) {
            return;
        }
        
        if (gp != null && gp.val % 2 == 0) {
            sum += root.val;
        }
        
        helper(root.left, root, parent);
        helper(root.right, root, parent);
    }
}