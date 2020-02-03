/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC669 {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        return helper(root, L, R);
    }
    
    private TreeNode helper(TreeNode root, int l, int r) {
        if (root == null) {
            return null;
        }
        
        TreeNode node = new TreeNode(root.val);
        node.left = helper(root.left, l, r);
        node.right = helper(root.right, l, r);
        
        //如果当前节点的值小于下限，那么将其右子树返回给父亲节点
        if (node.val < l) {
            return node.right;
        }
        
        //如果当前节点的值大于下限，那么将其左子树返回给父亲节点
        if (node.val > r) {
            return node.left;
        }
        
        //在上下界中，正常返回
        return node;
    }
}