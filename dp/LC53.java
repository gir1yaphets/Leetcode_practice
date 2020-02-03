class LC53 {
    /**
     * dp[i]:从0~i的subarray的和的最大值
     * 整体的最大值不一定出现在dp[n-1] 
     * e.g [-2,1,-3,4,-1,2,1,-5,4] 到index 6的最大值是6 但是到index 7的时候subarray最大值是1
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        
        int[] dp = new int[n];
        
        dp[0] = nums[0];
        int max = dp[0];
        
        for (int i = 1; i < n; i++) {
            //到index i的和最大的subarray 要么是dp[i-1]在连上当前nums[i],要么是nums[i]比之前的subarray的和都大，直接以nums[i]自己单独为subarray
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            max = Math.max(dp[i], max);
        }
        
        return max;
    }
}