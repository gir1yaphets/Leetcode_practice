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
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        
        return helper(n, 1, n);
    }
    
    private List<TreeNode> helper(int n, int start, int end) {
        List<TreeNode> list = new ArrayList<>(); //注意 这里是临时变量 返回每一层的结果
        
        if (start > end) return list;
        
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftList = helper(n, start, i - 1);
            List<TreeNode> rightList = helper(n, i + 1, end);
            
            if (leftList.size() == 0 && rightList.size() == 0) {
                list.add(new TreeNode(i));
            } else if (leftList.size() == 0) {
                for (TreeNode right : rightList) {
                    TreeNode root = new TreeNode(i);
                    root.right = right;

                    list.add(root);
                }
            } else if (rightList.size() == 0) {
                for (TreeNode left : leftList) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;

                    list.add(root);
                }
            } else {
                for (TreeNode left : leftList) {
                    for (TreeNode right : rightList) {
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        root.right = right;

                        list.add(root);
                    }
                }
            }
        }
        
        return list;
    }
}