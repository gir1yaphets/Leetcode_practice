class Solution {
    //DFS
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, 0, res);
        return res;
    }
    
    private void helper(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) return;
        
        if (res.size() <= level) {
            res.add(level, new ArrayList<>());
        }
        
        res.get(level).add(root.val);
        
        helper(root.left, level + 1, res);
        helper(root.right, level + 1, res);
    }

    //BFS
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> line = new ArrayList<>();
            res.add(line);
            
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                line.add(node.val);
            
                if (node != null) {
                    if (node.left != null) {
                        q.offer(node.left);
                    }

                    if (node.right != null) {
                        q.offer(node.right);
                    }
                }
            }
        }
        
        return res;
    }
}