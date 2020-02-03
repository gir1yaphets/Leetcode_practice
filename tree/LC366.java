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
    
    public List<List<Integer>> findLeaves(TreeNode root) {
        if (root == null) return res;
        helper(root);
        return res;
    }
    
    private int helper(TreeNode root) {
        if (root == null) return -1;
        
        int height = Math.max(helper(root.left), helper(root.right)) + 1;
        
        if (res.size() <= height) {
            res.add(new ArrayList<>());
        }
        
        res.get(height).add(root.val);
        
        return height;
    }
}