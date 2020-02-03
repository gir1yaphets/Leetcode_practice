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
    private int max = 0;
    
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        helper(root, map);
        
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                list.add(entry.getKey());
            }
        }
        
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        
        return res;
    }
    
    private int helper(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) {
            return 0;
        }
        
        int left = helper(root.left, map);
        int right = helper(root.right, map);
        
        //从底部开始向上求和，用map记录以每一个点作为root的sum
        int sum = root.val + left + right;
        int count = map.getOrDefault(sum, 0) + 1;
        map.put(sum, count);
        
        max = Math.max(max, count);
        
        return sum;
    }
}