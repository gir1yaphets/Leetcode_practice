/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC979 {
    private int res = 0;
    
    public int distributeCoins(TreeNode root) {
        helper(root);
        return res;
    }
    
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int l = helper(root.left);
        int r = helper(root.right);
        
        //把当前节点左右子树多余的个数都转移到父亲节点，这样左右子树就平衡了
        res += Math.abs(l) + Math.abs(r);
    
        //当前节点多出来的节点就等于左右子树多出来的节点个数 + 本身的个数 - 1 因为最后只能留一个
        return l + r + root.val - 1;
    }
}