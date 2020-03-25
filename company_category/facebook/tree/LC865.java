/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
package tree;
class LC865 {
    private int max = 0;
    private TreeNode res = null;
    
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        helper(root, 0);
        return res;
    }
    
    private int helper(TreeNode root, int level) {
        max = Math.max(max, level);
        
        if (root == null) {
            return level;
        }
        
        int lm = helper(root.left, level + 1);
        int rm = helper(root.right, level + 1);
        
        if (lm == max && rm == max) {
            res = root;
        }
        
        return Math.max(lm, rm);
    }
}