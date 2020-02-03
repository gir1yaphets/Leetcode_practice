class Solution {
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> permute(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        dfs(nums, new ArrayList<>(), visited);
        return res;
    }
    
    private void dfs(int[] nums, List<Integer> sol, boolean[] visited) {
        if (sol.size() == nums.length) {
            res.add(new ArrayList<>(sol));
            return;
        }
        
        /**
         * 这个和其他的subset和combination不一样，这个因为每组解都是3个，所以不是每次以一个新的起点做dfs
         */
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sol.add(nums[i]);
                
                dfs(nums, sol, visited);
                
                visited[i] = false;
                sol.remove(sol.size() - 1);
            }
        }
    }
}