/**
 * T: O(2^n) 对于每一个数字，可以选或者不选，既有两种选择
 * S: O(n) 最多的递归层数是每一个数字都选
 */
class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0, new ArrayList<>());
        return res;
    }
    
    private void dfs(int[] nums, int start, List<Integer> sol) {
        res.add(new ArrayList<>(sol));
        
        if (start == nums.length) {
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            sol.add(nums[i]);
            dfs(nums, i + 1, sol);
            sol.remove(sol.size() - 1);
        }
    }
    
}