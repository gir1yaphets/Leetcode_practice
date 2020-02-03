class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 0) return 0;
        
        //dp[i][0]:第i天持有0股的可以获得的利润; dp[i][1]:第i天持有1股可获得的利润
        int[][] dp = new int[n][2]; 
        
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        
        int max = 0;
        
        for (int i = 1; i < n; i++) {
            //第i天持有0股的利润 = Max(第i-1天持有0股的利润， 第i-1天持有1股并且在第i天卖出获得的利润)
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]); //dp[i][1]不可能是最大利润，只是为了计算dp[i-1][1]用
            
            max = Math.max(dp[i][0], max);
        }
        
        return max;
    }
}