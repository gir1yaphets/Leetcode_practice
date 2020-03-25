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
class LC1123 {
    private int maxDepth = 0;
    private TreeNode lca = null;
    
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        helper(root, 0);
        return lca;
    }
    
    private int helper(TreeNode root, int level) {
        maxDepth = Math.max(maxDepth, level);
        
        if (root == null) {
            return level;
        }
        
        int leftDepth = helper(root.left, level + 1);
        int rightDepth = helper(root.right, level + 1);
        
        //如果存在某一个节点 左子树的最大深度=右子树的最大深度=整个树的最大深度 那么该点就是最深叶子节点的common ancester
        if (leftDepth == maxDepth && rightDepth == maxDepth) {
            lca = root;
        }
        
        return Math.max(leftDepth, rightDepth);
    }
}