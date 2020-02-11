import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */
class LC863 {
    private Map<TreeNode, Integer> map = new HashMap<>();
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> res = new ArrayList<>();
        find(root, target);
        helper(root, map.get(root), K, res);
        return res;
    }
    
    /**
     * 将target节点标记为level 0, 然后将其上面的父亲节点的level依次标记为level + 1, 其下面的节点不用标记
     * @param root
     * @param target
     * @return
     */
    private int find(TreeNode root, TreeNode target) {
        if (root == null) {
            return -1;
        }
        
        if (root == target) {
            map.put(root, 0);
            return 0;
        }
        
        int left = find(root.left, target);
        if (left >= 0) {
            map.put(root, left + 1);
            return left + 1;
        }
        
        int right = find(root.right, target);
        if (right >= 0) {
            map.put(root, right + 1);
            return right + 1;
        }
        
        return -1;
    }
    
    /**
     * 如果该节点存在于map中，则直接取该节点相对于target节点的距离，否则用之前取过的距离+1
     *      1
     *    /  \
     *   2   3
     * 如果2是target节点 那么1存在于map中 level就是1
     * 3不在map中，用1的level + 1，所以3距离2的距离就是2
     */
    private void helper(TreeNode root, int level, int K, List<Integer> res) {
        if (root == null) return;
        if (map.containsKey(root)) {
            level = map.get(root);
        }
        
        if (level == K) {
            res.add(root.val);
        }
        
        helper(root.left, level + 1, K, res);
        helper(root.right, level + 1, K, res);
    }
}