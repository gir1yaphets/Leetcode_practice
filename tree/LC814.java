/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC814 {
    public TreeNode pruneTree(TreeNode root) {
        return helper(root);
    }
    
    private TreeNode helper(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        TreeNode node = new TreeNode(root.val);
        node.left = helper(root.left);
        node.right = helper(root.right);
        
        //leaf node, 注意这里不是root.left == null && root.right == null, 
        //如果当前这个新节点是叶子节点并且跟节点还是0，那么直接把当前节点去掉，返回null给上层
        if (node.left == null && node.right == null) {
            if (node.val == 0) {
                return null;
            }
        }
        
        return node;
    }
}