public class LC337 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 错误做法，从root开始枪，和不从root开始枪，隔一层取一次反
     * 错误反例[4,1,2,3]，1和2都不抢，中间隔了两个
     * 错误原因：并不是隔一层抢一次，而是相邻层不能抢，统计出最大值
     */
    public int rob_wrong(TreeNode root) {
        return Math.max(dfs_wrong(root, 1), dfs_wrong(root, 0));
    }
    
    private int dfs_wrong(TreeNode root, int flag) {
        if (root == null) return 0;
        
        int sum = 0;
        
        if (flag == 1) sum += root.val;
        
        int left = dfs_wrong(root.left, 1 - flag);
        int right = dfs_wrong(root.right, 1 - flag);
        
        sum += left + right;
        
        return sum;
    }

    /**
     * 正确做法
     * res[0]代表当前root不抢，res[1]代表当前root抢
     */
    public int rob(TreeNode root) {
        return Math.max(dfs(root)[0], dfs(root)[1]);
    }
    
    private int[] dfs(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        int[] res = new int[2];
        
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        
        return res;
    }
}