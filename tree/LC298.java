/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC298 {
    /**
     * 处理不了先连续然后不连续又连续的情况 left，right会基于之前的继续计数，缺少清空的过程
     * @param root
     * @return
     */
    public int longestConsecutive_wrong(TreeNode root) {
        if (root == null) return 0;
        return helper(root);
    }
    
    private int helper(TreeNode root) {
        if (root == null) {
            return 1;
        }

        int left = helper(root.left);
        int right = helper(root.right);
        
        int lm = left, rm = right;
        if (root.left != null && root.val == root.left.val - 1) {
            lm = left + 1;
        }
        
        if (root.right != null && root.val == root.right.val - 1) {
            rm = right + 1;
        }
        
        return Math.max(lm, rm);
    }

    /**
     * correct
     */
    int max = 0;
    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        helper(root);
        return max;
    }
    
    private int helper(TreeNode root) {
        if (root == null) {
            return 1;
        }

        int left = helper(root.left);
        int right = helper(root.right);
        
        int lm = left, rm = right;
        if (root.left != null) {
            if (root.val == root.left.val - 1) {
                lm = left + 1;
            } else {
                lm = 1;
            }
        }
        
        if (root.right != null) {
            if (root.val == root.right.val - 1) {
                rm = right + 1;
            } else {
                rm = 1;
            }
        }
        
        //lm, rm有可能被清空 所以要存住最大的
        max = Math.max(max, Math.max(lm, rm));
        
        return Math.max(lm, rm);
    }
}