import java.util.*;

class LC46 {
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> permute(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        dfs(nums, new ArrayList<>(), visited);
        return res;
    }
    
    /**
     * 不需要用start记录起点 每次进入dfs都对整个数组进行遍历 找一个没用过的add到solution里
     * @param nums
     * @param sol
     * @param visited
     */
    private void dfs(int[] nums, List<Integer> sol, boolean[] visited) {
        if (sol.size() == nums.length) {
            res.add(new ArrayList<>(sol));
            return;
        }
        
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