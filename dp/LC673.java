class LC673 {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        
        int[] dp = new int[n];
        int[] cnt = new int[n];//到index为i的上升子序列的个数
        int max = 0;
        int res = 1;
        
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            cnt[i] = 1;
            
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    /**
                     * 如果有子序列长度相同的，更新改点的上升子序列个数 
                     * 1,3,5,4,7
                     * 第一次dp[4] == 4是1,3,5,7，这时候cnt[4] = 1
                     * 第二次dp[4] == 4是1,4,5,7, 这时候cnt[4] += cnt[3] = 2
                     */
                    if (dp[i] == dp[j] + 1) {
                        cnt[i] += cnt[j];
                    }
                    
                    /**
                     * 如果当前从j开始的上升子序列列长度更长，那么到i的上升子序列个数就等于到j的上升子序列个数
                     * 1,3,5,4,7,8
                     * 到8的上升子序列个数就等于到7的上升子序列个数
                     */
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j];
                    }
                }
            }
            
            if (max == dp[i]) {
                res += cnt[i];
            }
            
            if (max < dp[i]) {
                max = dp[i];
                res = cnt[i];
            }
        }
        
        
        return res;
    }
}