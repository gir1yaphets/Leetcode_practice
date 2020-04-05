
public class LC213 {
    public int rob(int[] nums) {
        int n = nums.length;
        
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        
        int max = 0;
        //rob from 0
        int dp1 = 0, dp2 = 0;
        
        for (int i = 0; i < n - 1; i++) {
            int dp = Math.max(dp1, dp2 + nums[i]);
            dp2 = dp1;
            dp1 = dp;
        }
        
        max = dp1;
        
        //rob from 1
        dp1 = 0; dp2 = 0;
        for (int i = 1; i < n; i++) {
            int dp = Math.max(dp1, dp2 + nums[i]);
            dp2 = dp1;
            dp1 = dp;
        }
        
        max = Math.max(max, dp1);
        return max;
    }
}