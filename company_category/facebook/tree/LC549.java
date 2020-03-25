package tree;
class LC549 {
    private int max = 0;
    
    public int longestConsecutive(TreeNode root) {
        helper(root);
        return max;
    }
    
    private int[] helper(TreeNode root) {
        if (root == null) {
            //[incre, decre]
            return new int[]{0, 0};
        }
        
        int incre = 1, decre = 1;
        
        int[] left = helper(root.left);
        
        if (root.left != null) {
            if (root.left.val == root.val + 1) {
                incre = left[0] + 1;
            } else if (root.left.val == root.val - 1) {
                decre = left[1] + 1;
            }
        }
        
        
        int[] right = helper(root.right);
        
        if (root.right != null) {
            if (root.right.val == root.val + 1) {
                incre = Math.max(right[0] + 1, incre);
            } else if (root.right.val == root.val - 1) {
                decre = Math.max(right[1] + 1, decre);
            }
        }
        
        max = Math.max(max, incre + decre - 1);
        
        return new int[]{incre, decre};
    }
}