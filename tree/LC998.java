/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC998 {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        return helper(root, val);
    }
    
    private TreeNode helper(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        
        if (val > root.val) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        } 
        
        //因为该val是append到拆分后数组的末尾，所有只能插入到右子树上
        root.right = helper(root.right, val);
        
        return root;
    }
}