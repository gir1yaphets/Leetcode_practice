class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates, target, 0, 0, new ArrayList<>());
        return res;
    }
    
    private void dfs(int[] can, int target, int sum, int start, List<Integer> sol) {
        if (sum == target) {
            res.add(new ArrayList<>(sol));
            return;
        }
        
        for (int i = start; i < can.length; i++) {
            if (sum + can[i] > target) {
                continue;
            }
            
            //当遇到重复的元素不放到list中
            if (i > start && can[i] == can[i - 1]) {
                continue;
            }
            
            sol.add(can[i]);
            dfs(can, target, sum + can[i], i + 1, sol);
            sol.remove(sol.size() - 1);
        }
    }
}