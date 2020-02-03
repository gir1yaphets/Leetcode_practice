//1.递归:时间复杂度O(n)每个节点走一遍，空间复杂度:最好O(h)平衡，最坏O(n)
//2.非递归 stack实现
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        
        TreeNode curr = root;
        
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        
        return res;
    }
}