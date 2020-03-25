package tree;
import java.util.*;

class LC515 {
    /**
     * BFS
     */
    public List<Integer> largestValues_bfs(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            int max = Integer.MIN_VALUE;
            
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                
                if (node.left != null) {
                    q.offer(node.left);
                }
                
                if (node.right != null) {
                    q.offer(node.right);
                }
                
                max = Math.max(max, node.val);
            }
            
            res.add(max);
        }
        
        
        return res;
    }


    /**
     * DFS
     */
    private List<Integer> res = new ArrayList<>();
    
    public List<Integer> largestValues(TreeNode root) {
        dfs(root, 0);
        return res;
    }
    
    private void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        
        if (level >= res.size()) {
            res.add(root.val);
        } else {
            res.set(level, Math.max(res.get(level), root.val));
        }
        
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }
}