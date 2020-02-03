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
    class Pair {
        int height;
        int val;
        
        public Pair(int h, int v) {
            height = h;
            val = v;
        }
    }
    
    private int max = 0;
    
    /**
     * 3ms
     * @param root
     * @return
     */
    public int longestUnivaluePath(TreeNode root) {
        helper(root, 0);
        return max;
    }
    
    /**
     * 自己的把node节点通过返回值返回去，这个方法将父亲节点的值传下去
     * @param root
     * @param val
     * @return
     */
    private int helper(TreeNode root, int val) {
        if (root == null) return 0;
        
        int left = helper(root.left, root.val);
        int right = helper(root.right, root.val);

        //计算以当前节点为最高节点的最长高度
        //因为下面应判断了，当节点的值和父亲节点不相等的时候返回0，所以这里可以无条件更新max，不需要再判断是否和父亲节点相等
        max = Math.max(max, left + right);
        
        if (root.val == val) {
            //当前节点和父亲节点相同的时候更新当前节点的高度，
            return Math.max(left, right) + 1;
        }        
        
        return 0;
    }


    public int longestUnivaluePath_mine_6ms(TreeNode root) {
        helper_mine(root);
        return max;
    }
    
    private Pair helper_mine(TreeNode root) {
        if (root == null) return new Pair(0, -1);
        
        Pair left = helper(root.left);
        Pair right = helper(root.right);
        
        int path = 0;
        int lh = 0, rh = 0;
        if (root.val == left.val) {
            path += left.height;
            lh = left.height;
        }
        
        if (root.val == right.val) {
            path += right.height;
            rh = right.height;
        }
        
        max = Math.max(max, path);
        
        return new Pair(Math.max(lh, rh) + 1, root.val);
    }
}