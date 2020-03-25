import java.util.*;

class LC742 {
    private Map<TreeNode, Integer> map = new HashMap<>();
    private int min = Integer.MAX_VALUE;
    private int res = 0;
        
    public int findClosestLeaf(TreeNode root, int k) {
        find(root, k);
        dfs(root, map.get(root));
        return res;
    }
    
    private int find(TreeNode root, int k) {
        if (root == null) return -1;
        
        if (root.val == k) {
            map.put(root, 0);
            return 0;
        }
        
        int left = find(root.left, k);
        if (left >= 0) {
            map.put(root, left + 1);
            return left + 1;
        }
        
        int right = find(root.right, k);
        if (right >= 0) {
            map.put(root, right + 1);
            return right + 1;
        }
    
        return -1;
    }
    
    private void dfs(TreeNode root, int level) {
        if (root == null) return;
        
        if (map.containsKey(root)) {
            level = map.get(root);
        }
        
        if (root.left == null && root.right == null) {
            if (level < min) {
                min = level;
                res = root.val;
            }
        }
        
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }
}