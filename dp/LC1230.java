
public class LC1230 {
    public double probabilityOfHeads(double[] prob, int target) {
        int n = prob.length;
        //dp[i][j]前i个元素中有j个正面 = dp[i-1][j-1] * pro[i] + dp[i-1][j] * (1 - prob[i])
        
        double[][] dp = new double[n+1][n+1];
        dp[0][0] = 1;
        
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i-1][0] * (1 - prob[i-1]);
        }
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i-1][j-1] * prob[i-1] + dp[i-1][j] * (1 - prob[i-1]);
            }
        }
        
        return dp[n][target];
    }

    public double probabilityOfHeads_1D(double[] prob, int target) {
        double[] dp = new double[target + 1];
        dp[0] = 1.0;
        
        for (int i = 0; i < prob.length; ++i) {
            for (int k = Math.min(i + 1, target); k >= 0; --k) {
                dp[k] = (k > 0 ? dp[k - 1] : 0) * prob[i] + dp[k] * (1 - prob[i]);
            }
        }
        
        return dp[target];
    }
}