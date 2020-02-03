/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    /**
     * Correct
     */
    private List<Integer> res = new ArrayList<>();
    
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) return res;
        
        res.add(root.val);
        helper(root.left, true, false);
        helper(root.right, false, true);
        return res;
    }
    
    private void helper(TreeNode root, boolean lb, boolean rb) {
        if (root == null) return;
        
        if (lb) {
            res.add(root.val);
        }
        
        if (!lb && !rb && root.left == null && root.right == null) {
            res.add(root.val);
        }
        
        //当前如果是左边界，再往左走 还是左边界；当前左走下一层还能够是右边界的条件：当前是右边界并且当前节点的右点为null
        helper(root.left, lb, rb && root.right == null);
        helper(root.right, lb && root.left == null, rb);
               
        if (rb) res.add(root.val);
    }

    /**
     * wrong but don't know why
     */
    private List<Integer> res = new ArrayList<>();
    private List<Integer> resRight = new ArrayList<>();
    
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) return res;
        
        res.add(root.val);
        helper(root, root.left, true, false, true, false);
        helper(root, root.right, false, true, false, true);
        
        res.addAll(resRight);
        return res;
    }
    
    private void helper(TreeNode prev, TreeNode root, boolean leftSubtree, boolean rightSubtree, boolean leftNode, boolean rightNode) {
        if (root == null) return;
        
        if (isLeftBoundry(prev, root, leftSubtree, rightSubtree, leftNode, rightNode)) {
            res.add(root.val);
        } else if (isRightBoundry(prev, root, leftSubtree, rightSubtree, leftNode, rightNode)) {
            resRight.add(0, root.val);
        }
        
        helper(root, root.left, leftSubtree, rightSubtree, true, false);
        
        helper(root, root.right, leftSubtree, rightSubtree, false, true);
    }
    
    private boolean isLeftBoundry(TreeNode prev, TreeNode root, boolean leftSubtree, boolean rightSubtree, boolean leftNode, boolean rightNode) {
        if (root.left == null && root.right == null) return true;
        
        if (leftSubtree && leftNode || leftSubtree && rightNode && prev.left == null) {
            return true;
        }
        
        return false;
    }
    
    private boolean isRightBoundry(TreeNode prev, TreeNode root, boolean leftSubtree, boolean rightSubtree, boolean leftNode, boolean rightNode) {
        if (root.left == null && root.right == null) return false;
        
        if (rightSubtree && rightNode || rightSubtree && leftNode && prev.right == null) {
            return true;
        }
        
        return false;
    }
    
    
}