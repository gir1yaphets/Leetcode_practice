/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int[] in, post;
    private Map<Integer, Integer> map = new HashMap<>();
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.in = inorder;
        this.post = postorder;
        
        for (int i = 0; i < in.length; i++) map.put(in[i], i);
        
        return helper(post.length - 1, 0, in.length - 1);
    }
    
    private TreeNode helper(int poststart, int instart, int inend) {
        if (poststart < 0 || instart > inend) return null;
        
        if (instart == inend) return new TreeNode(in[instart]);
        
        TreeNode root = new TreeNode(post[poststart]);
        
        int rootindex = map.get(post[poststart]);
        
        root.left = helper(poststart - (inend - rootindex + 1), instart, rootindex - 1);
            
        root.right = helper(poststart - 1, rootindex + 1, inend);
        
        return root;
    }
}