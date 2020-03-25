package tree;
import java.util.*;

class LC652 {
    private Map<String, Integer> map = new HashMap<>();
    private List<TreeNode> res = new ArrayList<>();
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        
        return res;
    }
    
    private String dfs(TreeNode root) {
        if (root == null) return "#";
        
        String serial = root.val + "-" + dfs(root.left) + "-" + dfs(root.right);
        
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        
        if (map.get(serial) == 2) {
            res.add(root);
        }
        
        return serial;
    }
    
}