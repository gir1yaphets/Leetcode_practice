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
    private TreeNode preNode, first, second;
    
    public void recoverTree(TreeNode root) {
        helper(root);
        swap();
    }
    
    private void helper(TreeNode root) {
        if (root == null) return;
        
        helper(root.left);
        
        //当第二次出现pre.val > root.val 则发现了的被交换的第二个值 second记录第一次发现的值 first被更新为第二次发现的被交换的值
        //e.g orginal : 1 2 3 4 5 6
        //swaped: 1 5 3 4 2 6
        //第一次发现5>3 fist = 3 second = 5; 第二次发现4>2 更新first = 2 不再更新second 所以被交换的元素就是2，5
        if (preNode != null && preNode.val > root.val) {
            first = root;
            
            if (second == null) {
                second = preNode;
            } else {
                return;
            }
        }
        
        preNode = root;
        
        helper(root.right);
    }
    
    private void swap() {
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}