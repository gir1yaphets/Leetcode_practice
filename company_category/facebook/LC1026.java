/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC1026 {
    public int maxAncestorDiff(TreeNode root) {
        if (root == null) return 0;
        
        return helper(root, root.val, root.val);
    }
    
    private int helper(TreeNode root, int max, int min) {
        //每次走到根节点的时候统计这条路径上的最大最小值的差
        if (root == null) {
            return max - min;
        }        
        
        max = Math.max(max, root.val);
        min = Math.min(min, root.val);
        
        int leftDiff = helper(root.left, max, min);
        int rightDiff = helper(root.right, max, min);
        
        //将左右子树差值的最大值返回给上层
        return Math.max(leftDiff, rightDiff);
    }
}