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
    class RangeNode {
        private int max;
        private int min;
        private int size;

        public RangeNode(int _size, int _min, int _max) {
            size = _size;
            min = _min;
            max = _max;
        }
    }
    
    private int max = 0;
    
    public int largestBSTSubtree(TreeNode root) {
        helper(root);
        return max;
    }
    
    private RangeNode helper(TreeNode root) {
        if (root == null) return new RangeNode(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        
        RangeNode left = helper(root.left);
        RangeNode right = helper(root.right);
 
        //只要有一个点的子树不满足 该点一直向上返回size -1，上面都不用再看
        if (left.size == -1 || right.size == -1 || left.max >= root.val || right.min <= root.val) {
            return new RangeNode(-1, 0, 0);
        }
        
        int size = left.size + 1 + right.size;
        max = Math.max(size, max);
        
        //将root和left.min最为min，root和right.max作为max返回给上层
        return new RangeNode(size, Math.min(root.val, left.min), Math.max(root.val, right.max));
    }
}