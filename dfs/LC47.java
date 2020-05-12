import java.util.*;
class LC47 {
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length == 0) return res;
        
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];
        
        dfs(nums, visited, new ArrayList<>());
        
        return res;
    }
    
    private void dfs(int[] nums, boolean[] visited, List<Integer> sol) {
        if (sol.size() == nums.length) {
            res.add(new ArrayList<>(sol));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            //e.g [1,1,2] 正常情况第一个1使用过，这个时候可以使用第二个1
            //但当递归到第二个1的时候，若前一个元素与它相等，并且还没有被使用过，那么这个元素就不需要再使用，因为再使用这个元素作为新的起点会和它前一个元素差生一样的集合
            if (visited[i] || (i > 0 && !visited[i - 1] && nums[i] == nums[i-1])) {
                continue;
            }
            
            visited[i] = true;
            sol.add(nums[i]);
            
            dfs(nums, visited, sol);
            
            visited[i] = false;
            sol.remove(sol.size() - 1);
        }
    }
}