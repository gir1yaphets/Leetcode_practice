package tree;
import java.util.*;
/**
 *  这道题的意思是交换左子树和右子树能满足match voyage，并且将被交换子树的父节点输出
 */
class LC971 {
    private int index = 0;
    private List<Integer> res = new ArrayList<>();
    
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        dfs(root, voyage);
        
        return res;
    }
    
    private boolean dfs(TreeNode root, int[] v) {
        if (root == null) return true;
        
        //先判断当前节点和对应index的值是否相等，如果不等直接返回false，以为之前不等的时候已经左了交换，如果交换完还不相等则肯定无法match
        if (root.val != v[index]) {
            res = Arrays.asList(-1);
            return false;
        }
        
        index += 1;
        
        //如果左子树节点值和下一个不等，则需要在这个几点将其左子树和右子树交换，即先遍历右子树再遍历左子树
        if (root.left != null && root.left.val != v[index]) {
            res.add(root.val);
            return dfs(root.right, v) && dfs(root.left, v);
        }
        
        return dfs(root.left, v) && dfs(root.right, v);
    }
}