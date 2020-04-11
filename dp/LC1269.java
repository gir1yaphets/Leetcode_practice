
public class LC1269 {
    /**
     * MLE
     */
    public int numWays(int steps, int arrLen) {
        long[][] dp = new long[steps+1][arrLen];
        dp[0][0] = 1;
        int mod = 1000000007;
        
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j < arrLen; j++) {
                dp[i][j] = dp[i-1][j];
                if (j - 1 >= 0) dp[i][j] = (dp[i][j] + dp[i-1][j-1]) % mod;
                if (j + 1 < arrLen)  dp[i][j] = (dp[i][j] + dp[i-1][j+1]) % mod;
            }
        }
        
        return (int) dp[steps][0];
    }

    public int numWays_1d(int steps, int arrLen) {
        if(arrLen <= 1) return arrLen;
        int[] dp = new int[arrLen];
        dp[0] = 1;
        int mod = 1000000007;
        
        for (int i = 1; i <= steps; i++) {
            int[] next = new int[arrLen];
            for (int j = 0; j <= Math.min(arrLen - 1, i); j++) {
                //dp[j]: 走当前i-1个step能到达j的方法, next[j]走i个step能到达j的方法
                next[j] = dp[j];
                if (j - 1 >= 0)  next[j] = (next[j] + dp[j-1]) % mod;
                if (j + 1 < arrLen) next[j] = (next[j] + dp[j+1]) % mod;
            }
            
            dp = next;
        }
        
        return (int) dp[0];
    }

    public int numWays_2d(int steps, int arrLen) {
        long[][] dp = new long[2][arrLen];
        dp[0][0] = 1;
        int mod = 1000000007;
        
        for (int i = 1; i <= steps; i++) {
            //这里有个剪枝，当走了i个step的时候最远可以走到i的位置
            for (int j = 0; j <= Math.min(arrLen - 1, i); j++) {
                dp[i%2][j] = dp[(i-1)%2][j];
                if (j - 1 >= 0) dp[i%2][j] = (dp[i%2][j] + dp[(i-1)%2][j-1]) % mod;
                if (j + 1 < arrLen)  dp[i%2][j] = (dp[i%2][j] + dp[(i-1)%2][j+1]) % mod;
            }
        }
        
        return (int) dp[steps%2][0];
    }
}