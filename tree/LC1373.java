public class LC1373 {
    class Node {
        int lower;
        int upper;
        int sum;
        
        public Node(int l, int u, int s) {
            lower = l;
            upper = u;
            sum = s;
        }
    }
    
    private int max = 0;
    
    public int maxSumBST(TreeNode root) {
        dfs(root);
        return max;
    }
    
    private Node dfs(TreeNode root) {
        if (root == null) return new Node(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        
        Node left = dfs(root.left);
        Node right = dfs(root.right);
        
        //当走到最末端的null节点的时候并没有返回null，只有当不满足bst的时候向上一直返回null
        if (!(left != null && right != null && root.val > left.upper && root.val < right.lower)) {
            return null;
        }

        int sum = root.val + left.sum + right.sum;
        max = Math.max(max, sum);
        
        int lower = Math.min(root.val, right.lower);
        int upper = Math.max(root.val, left.upper);
        
        return new Node(lower, upper, sum);
    }
}