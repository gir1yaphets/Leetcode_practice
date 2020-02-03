class LC698 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        
        boolean[] visited = new boolean[n];
        if (sum % k != 0) {
            return false;
        }
        
        int target = sum / k;
        
        //dfs
        return dfs(nums, k, 0, 0, target, visited);
    }
    
    /**
     * 
     * @param nums
     * @param k 组数
     * @param start 起始点
     * @param sum 当前和
     * @param target 每组的和
     * @param visited 是否访问过
     * @return
     */
    private boolean dfs(int[] nums, int k, int start, int sum, int target, boolean[] visited) {
        //当只剩一组的时候 肯定是可以 因为前面已经判断过了能否整除k
        if (k == 1) return true;
        
        if (sum > target) return false;
        
        //继续dfs找下一组
        if (sum == target) return dfs(nums, k - 1, 0, 0, target, visited);
        
        for (int i = start; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                
                sum += nums[i];
                
                if (dfs(nums, k, i + 1, sum, target, visited)) {
                    return true;   
                }
                    
                sum -= nums[i];
                visited[i] = false;
            }
        }
        
        return false;
    }
}