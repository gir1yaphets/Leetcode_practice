/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC572 {
    /**
     * 时间复杂度是O(mn), 最坏情况s是一颗单边树并且全是1，t也是单边树只有1个1，那么对于s中的每一个节点都要判断是否和t是相等的，所以m*n
     * 空间复杂度O(m),s中每个节点都要遍历一次，当是单边树的时候空间复杂度是O(m)
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return helper(s, t);
    }
    
    private boolean helper(TreeNode s, TreeNode t) {
        if (s == null || t == null) return s == t;
        
        //对于每个节点判断该根是不是一样的subtree, 如果根节点不同，equal方法直接返回false
        if (equal(s, t)) {
            return true;
        }
        
        //如果当前节点两棵树不同， 则判断s的左子树或者右子树是否有相同的
        return helper(s.left, t) || helper(s.right, t);
    }
    
    /**
     * 判断是相同树的条件: 1.根节点值相等 2.左右子树值也相等
     * 如果根节点不同，equal方法直接返回false
     * @param s
     * @param t
     * @return
     */
    private boolean equal(TreeNode s, TreeNode t) {
        if (s == null || t == null) return s == t;
        
        if (s.val != t.val) {
            return false;
        }
        
        return equal(s.left, t.left) && equal(s.right, t.right);
    }
}