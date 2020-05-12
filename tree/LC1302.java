import java.util.*;
public class LC1302 {
    public int deepestLeavesSum(TreeNode root) {
        if (root == null) return 0;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int sum = 0;
        
        while (!q.isEmpty()) {
            int size = q.size();
            sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                sum += node.val;
                
                if (node.left != null) {
                    q.offer(node.left);
                }
                
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        
        return sum;
    }

    private int maxLevel = 0, sum = 0;
    
    public int deepestLeavesSum_dfs(TreeNode root) {
        dfs(root, 0);
        return sum;
    }
    
    private void dfs(TreeNode root, int level) {
        if (root == null) return;
        
        if (root.left == null && root.right == null) {
            if (level > maxLevel) {
                sum = root.val;
                maxLevel = level;
            } else if (level == maxLevel) {
                sum += root.val;
            }
        }
        
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }
}