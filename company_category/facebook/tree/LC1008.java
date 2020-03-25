package tree;
class LC1008 {
    private int index = 0;

    public static void main(String[] args) {
        int[] preorder = {8,5,1,7,10,12};
        new LC1008().bstFromPreorder(preorder);
    }
    
    public TreeNode bstFromPreorder(int[] preorder) {
        return helper(preorder, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }
    
    private TreeNode helper(int[] preorder, int upper, int lower) {
        if (index == preorder.length) {
            return null;
        }
        
        int currVal = preorder[index];
        
        if (currVal < lower || currVal > upper) {
            return null;
        }
        
        TreeNode root = new TreeNode(currVal);
        
        index += 1;
        root.left = helper(preorder, currVal, lower);
        root.right = helper(preorder, upper, currVal);
        
        return root;
    }
}