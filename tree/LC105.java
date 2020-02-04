import java.util.HashMap;
import java.util.Map;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC105 {
    private int[] pre, in;
    private Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.pre = preorder;
        this.in = inorder;
        
        for (int i = 0; i < in.length; i++) {
            map.put(in[i], i);
        }
        
        return helper(0, 0, in.length - 1);
    }
    
    private TreeNode helper(int pres, int ins, int ine) {
        if (pres == pre.length || ins > ine) return null;
        
        if (ins == ine) return new TreeNode(in[ins]);
        
        TreeNode root = new TreeNode(pre[pres]);
        
        int rootIndex = map.get(pre[pres]);
        
        //root index of next left node is pres + 1
        root.left = helper(pres + 1, ins, rootIndex - 1);
        
        //root index of next right node is pres + (length of left subtree)
        root.right = helper(pres + rootIndex - ins + 1, rootIndex + 1, ine);
        
        return root;
    }
}