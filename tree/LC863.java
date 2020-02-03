import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class LC863 {
    private Map<TreeNode, Integer> map = new HashMap<>();
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        find(root, target);
        helper(root, map.get(root), K, res);
        return res;
    }
    
    private int find(TreeNode root, TreeNode target) {
        if (root == null) {
            return -1;
        }
        
        if (root == target) {
            map.put(root, 0);
            return 0;
        }
        
        int left = find(root.left, target);
        if (left >= 0) {
            map.put(root, left + 1);
            return left + 1;
        }
        
        int right = find(root.right, target);
        if (right >= 0) {
            map.put(root, right + 1);
            return right + 1;
        }
        
        return -1;
    }
    
    private void helper(TreeNode root, int level, int K, List<Integer> res) {
        if (root == null) return;
        if (map.containsKey(root)) {
            level = map.get(root);
        }
        
        if (level == K) {
            res.add(root.val);
        }
        
        helper(root.left, level + 1, K, res);
        helper(root.right, level + 1, K, res);
    }
}