/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC101 {
    public boolean isSymmetric(TreeNode root) {
        return root == null || helper(root.left, root.right);
    }
    
    private boolean helper(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }
        
        //当前层要的两个顶点要相等，否则不对称
        if (left.val != right.val) {
            return false;
        }
        
        //左子树的左节点和右子树的右节点比，左子树的右节点和右子树的左节点比
        return helper(left.left, right.right) && helper(left.right, right.left);
    }
}