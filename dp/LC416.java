
public class LC416 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 == 1) return false;
        
        int target = sum / 2;
        
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        
        // for (int i = 0; i <= target; i++) {
        //     for (int j = 0; j < nums.length; j++) {
        //         int num = nums[j];
        //         if (i >= num) {
        //             dp[i] = dp[i] || dp[i-num];
        //         }
        //     }
        // }
        
        for (int num : nums) {
            //target从大到小，避免某一个num被重复使用
            //[1,2,5]，如果从小到大dp[4] = dp[2] = true 这样2就相当于被重复使用
            //如果从大到小计算dp[4]的时候还没有计算dp[2]
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j-num];
            }
        }
        
        return dp[target];
    }
}