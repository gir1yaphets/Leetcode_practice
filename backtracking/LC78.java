class LC78 {
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0, new ArrayList<>());
        return res;
    }
    
    private void dfs(int[] nums, int start, List<Integer> sol) {
        res.add(new ArrayList<>(sol));
        
        for (int i = start; i < nums.length; i++) {
            sol.add(nums[i]);
            dfs(nums, i + 1, sol);
            sol.remove(sol.size() - 1);
        }
    }
}