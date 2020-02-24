/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC1339 {
    private int sum = 0;
    private long max = 1;
    
    public int maxProduct(TreeNode root) {
        sumHelper(root);
        proHelper(root);
        
        return (int) (max % ((int) (1e9 + 7)));
    }
    
    private void sumHelper(TreeNode root) {
        if (root == null) return;
        
        sum += root.val;
        
        sumHelper(root.left);
        sumHelper(root.right);
    }
    
    private long proHelper(TreeNode root) {
        if (root == null) return 0;
        
        long l = proHelper(root.left);
        long r = proHelper(root.right);
        
        long total = l + r + root.val;
        
        max = Math.max(max, (total * (sum - total)));
        
        return total;
    }
}