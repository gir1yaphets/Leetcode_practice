/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC1123 {
    private TreeNode lca;
    private int depth = 0;
    
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        helper(root, 0);
        return lca;
    }
    
    private int helper(TreeNode root, int level) {
        //depth是最大深度
        depth = Math.max(level, depth);
        
        if (root == null) return level;
        
        int leftDepth = helper(root.left, level + 1);
        int rightDepth = helper(root.right, level + 1);
        
        //左右都等于最大深度节点更新为lca
        if (leftDepth == depth && rightDepth == depth) {
            lca = root;
        }
        
        return Math.max(leftDepth, rightDepth);
    }
}