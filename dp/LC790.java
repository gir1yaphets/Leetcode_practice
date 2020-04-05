
public class LC790 {
    /**
     * dp[i][j]：i第i列，j是第j种状态(1:上下齐 2.上多出来一块 3.下多出来一块)
     */
    public int numTilings(int N) {
        if (N == 1) return 1;
        
        long[][] dp = new long[N+1][3];
        int mod = 1000000007;
        
        dp[0][0] = dp[1][0] = dp[2][0] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-2][0] + dp[i-1][1] + dp[i-1][2]) % mod;
            dp[i][1] = (dp[i-2][0] + dp[i-1][2]) % mod;
            dp[i][2] = (dp[i-2][0] + dp[i-1][1]) % mod;
        } 
        
        return (int)dp[N][0];
    }

    /**
     * 状态2和状态3是对称的，所以可以合并
     */
    public int numTilings_compress(int N) {
        long[][] dp = new long[N+1][2];
        int mod = 1000000007;
        
        dp[0][0] = dp[1][0] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-2][0] + 2 * dp[i-1][1]) % mod;
            dp[i][1] = (dp[i-2][0] + dp[i-1][1]) % mod;
        } 
        
        return (int)dp[N][0];
    }
}