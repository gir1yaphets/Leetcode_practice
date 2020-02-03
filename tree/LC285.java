/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC285 {
    private TreeNode res, pred;
    
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) return null;
        
        helper(root, p);
        
        return res;
    }
    
    private void helper(TreeNode root, TreeNode p) {
        if (root == null) return;
        
        helper(root.left, p);
        
        if (pred == p && res == null) {
            res = root;
            return;
        }
        
        pred = root;
        
        helper(root.right, p);
    }


    /**
     * 没太看懂...
     * @param root
     * @param p
     * @return
     */
    public TreeNode successor(TreeNode root, TreeNode p) {
        if (root == null)
        return null;

        if (root.val <= p.val) {
            return successor(root.right, p);
        } else {
            TreeNode left = successor(root.left, p);
            return (left != null) ? left : root;
        }
    }
}