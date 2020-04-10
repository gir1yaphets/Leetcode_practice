
public class LC1262 {
    /**
     * dp[i]: 存储nums中的和对3取余的最大值 e.g dp[1]: nums中对3取余等于1的最大值 ,[1,3,4] nums[1] = 1 + 3 = 4
     */
    public int maxSumDivThree(int[] nums) {
        int[] dp = {0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        
        for (int num : nums) {
            int[] next = new int[3];
            for (int i = 0; i < 3; i++) {
                //如果(i+num)%3 == 1 那么(dp[i] + num)取余也等于1，e.g i = 2, num = 5, dp[2]中存储的是对3取余等于2的最大值比如等于10，(2+5) % 3 == 1那么10%3也等于1
                //因为dp[i] = i * k
                next[(i + num) % 3] = Math.max(dp[(i + num) % 3], dp[i] + num);
            }
            dp = next;
        }
        
        return dp[0];
    }
}