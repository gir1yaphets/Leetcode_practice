
public class LC1220 {
    public int countVowelPermutation(int n) {
        long[][] dp = new long[n][5];
        int mod = 1000000007;
        
        for (int i = 0; i < 5; i++) {
            dp[0][i] = 1;
        }
        
        for (int i = 1; i < n; i++) {
            //a
            dp[i][0] = (dp[i-1][4] + dp[i-1][1] + dp[i-1][2]) % mod;
            
            //e
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % mod;
            
            //i
            dp[i][2] = (dp[i-1][1] + dp[i-1][3]) % mod;
            
            //o
            dp[i][3] = dp[i-1][2] % mod;
            
            //u
            dp[i][4] = (dp[i-1][3] + dp[i-1][2]) % mod;
        }
        
        long sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += dp[n-1][i];
        }
        
        return (int)(sum % mod);
    }
}