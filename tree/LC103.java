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
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        helper(root, 0);
        return res;
    }
    
    private void helper(TreeNode root, int level) {
        if (root == null) return;
        
        if (res.size() <= level) {
            res.add(level, new ArrayList<>());
        }
        
        if (level % 2 == 0) {
            res.get(level).add(root.val);
        } else {
            res.get(level).add(0, root.val);
        }
        
        helper(root.left, level + 1);
        helper(root.right, level + 1);
    }
}