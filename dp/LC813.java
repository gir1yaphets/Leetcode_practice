
public class LC813 {
    /**
     * dp[k][i]: 以i为长度的数组分成k所能获得的最大平均值
     * 在1~i中找一个分割点j，将前1~j分成k-1组，剩余j+1～i分成1组，求前i个元素分成k组的最大值
     */
    public double largestSumOfAverages(int[] A, int K) {
        int n = A.length;
        double[][] dp = new double[K + 1][n + 1];
        double[] sum = new double[n + 1]; //sum必须也是double的，否则有错误
        
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + A[i - 1];
            dp[1][i] = sum[i] / i;
        }
        
        for (int k = 2; k <= K; k++) {
            //i不能小于k，当k=2的时候，数组长度至少得有2
            for (int i = k; i <= n; i++) {
                for (int j = k - 1; j < i; j++) {
                    dp[k][i] = Math.max(dp[k][i], dp[k-1][j] + (sum[i] - sum[j]) / (i - j));
                }
            }
        }
        
        return dp[K][n];
    }

    /**
     * dfs memo v1
     */
    public double largestSumOfAverages_dfs_memo1(int[] A, int K) {
        int n = A.length;
        
        double[][] memo = new double[n+1][K+1];
        return dfs(A, K, 0, memo);
    }
    
    private double dfs(int[] A, int K, int start, double[][] memo) {
        if (start == A.length) return 0;
        if (K == 1) {
            double s = 0;
            for (int i = start; i < A.length; i++) {
                s += A[i];
            }
        
            memo[start][K] = s / (A.length - start);
            return memo[start][K];
        }
        
        if (memo[start][K] != 0) return memo[start][K];
        
        double res = 0;
        double sum = 0;
        for (int i = start; i < A.length; i++) {
            sum += A[i];
            res = Math.max(res, sum / (i - start + 1) + dfs(A, K - 1, i + 1, memo));
        }
        
        memo[start][K] = res;
        return res;
    }

    /**
     * dfs memo v2
     */
    private double[] sum;
    private double[][] memo;
    
    public double largestSumOfAverages_dfs_emo2(int[] A, int K) {
        int n = A.length;
        sum = new double[n + 1];
        memo = new double[K+1][n+1];
        
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + A[i - 1];
        }
        
        return dfs(A, n, K);
    }
    
    private double dfs(int[] A, int n, int K) {
        if (memo[K][n] > 0) return memo[K][n];
        
        if (K == 1) {
            memo[K][n] = sum[n] / n;
            return memo[K][n];
        }
        
        for (int i = K - 1; i < n; i++) {
            memo[K][n] = Math.max(memo[K][n], dfs(A, i, K - 1) + (sum[n] - sum[i]) / (n - i));
        }
        
        return memo[K][n];
    }
}