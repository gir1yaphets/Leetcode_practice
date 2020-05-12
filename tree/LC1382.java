import java.util.*;
public class LC1382 {
    public TreeNode balanceBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return dfs(list, 0, list.size() - 1);
    }
    
    private TreeNode dfs(List<Integer> list, int start, int end) {
        if (start > end) return null;
        if (start == end) return new TreeNode(list.get(start));
        
        int m = (start + end) / 2;
        
        TreeNode root = new TreeNode(list.get(m));
        root.left = dfs(list, start, m - 1);
        root.right = dfs(list, m + 1, end);
        
        return root;
    }
    
    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        
        inorder(root.left, list);
        
        list.add(root.val);
        
        inorder(root.right, list);
    }
}