class Solution {
    private int res = 0;
    
    /**
     * 两次dfs，一次dfs是算以某一个节点为root，找以这个节点为起点path和为sum的个数
     * 第二次dfs是以每一个节点作为一次起点
     * 时间复杂度:  最好O(nlogn) 平衡二叉树 高度O(h) h = logn 最坏n^2 链表
     * 空间复杂度: O(n)
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        
        helper(root, 0, sum);
        
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        
        return res;
    }
    
    private void helper(TreeNode root, int curr, int sum) {
        if (root == null) {
            return;
        }
        
        curr += root.val;
        
        if (curr == sum) {
            res += 1;
        }
        
        helper(root.left, curr, sum);
        helper(root.right, curr, sum);
    }
}