import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class LC1305 {
    private List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>();
    
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        helper(root1, l1);
        helper(root2, l2);
        
        List<Integer> res = new ArrayList<>();
        
        int p1 = 0, p2 = 0;
        while (p1 < l1.size() || p2 < l2.size()) {
            if (p1 == l1.size()) {
                res.add(l2.get(p2++));
            } else if (p2 == l2.size()) {
                res.add(l1.get(p1++));
            } else {
                res.add(l1.get(p1) < l2.get(p2) ? l1.get(p1++) : l2.get(p2++));
            }
        }
        
        return res;
    }
    
    private void helper(TreeNode root, List<Integer> list) {
        if (root == null) return;
        
        helper(root.left, list);
        
        list.add(root.val);
        
        helper(root.right, list);
    }
}