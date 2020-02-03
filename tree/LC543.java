/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int max = 0;

    /**
     * O(n)
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        helper(root);
        return max;
    }
    
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = helper(root.left);
        int right = helper(root.right);
        
        //统计每个节点的最长半径，既它的左子树高度 + 右子树高度
        max = Math.max(max, left + right);
        
        //对每个节点返回它的高度
        return Math.max(left, right) + 1;
    }
    
    /**
     * O(n^2)
     * @param root
     * @return
     */
    public int diameterOfBinaryTree_n2(TreeNode root) {
        if (root == null) {
            return max;
        }
        
        max = Math.max(max, height(root.left) + height(root.right));
        diameterOfBinaryTree(root.left);
        diameterOfBinaryTree(root.right);
        
        return max;
    }
    
    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}