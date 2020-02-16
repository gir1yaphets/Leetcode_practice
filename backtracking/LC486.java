class LC486 {
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n * n];
        return getScore(nums, 0, nums.length - 1, memo) >= 0;
    }
    
    public int getScore(int[] nums, int l, int r, int[] memo) {
        if (l == r) {
            return nums[l];
        }
        
        if (memo[l * nums.length + r] != 0) {
            return memo[l * nums.length + r];
        }
        
        //若nums[l]是当前Player取的值，对手就在[l+1, r]取， 若当前Player取nums[r],则对手就在[l, r-1]取
        //但是当前选手要取nums[l], nums[r]产生得分较大的
        int diff = Math.max(nums[l] - getScore(nums, l + 1, r, memo), nums[r] - getScore(nums, l, r - 1, memo));

        //把这个自区间产生的解记录下来
        memo[l * nums.length + r] = diff;

        return diff;
    }
}