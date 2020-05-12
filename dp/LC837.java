class LC837 {
    private double total = 0, valid = 0;
    
    public double new21Game_Wrong(int N, int K, int W) {
        dfs_Wrong(N, K, W, 0);
        return valid / total;
    }
    
    private void dfs_Wrong(int N, int K, int W, int sum) {
        if (sum >= K) {
            total += 1;;
            if (sum <= N) {
                valid += 1;;
            }
            return;
        }
        
        for (int i = 1; i <= W; i++) {
            dfs_Wrong(N, K, W, sum + i);
        }
    }

    public double new21Game_TLE(int N, int K, int W) {
        return dfs_TLE(N, K, W, 0);
    }
    
    private double dfs_TLE(int N, int K, int W, int sum) {
        if (sum >= K) {
            if (sum <= N) {
                return 1;
            }
            return 0;
        }
        
        double pos = 0;
        for (int i = 1; i <= W; i++) {
            pos += 1.0 / W * dfs_TLE(N, K, W, sum + i);
        }
        
        return pos;
    }

    public double new21Game_TLE_NW(int N, int K, int W) {
        double[] dp = new double[N + 1];
        
        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            //这一步等价于统计dp[i-w]到dp[i]的和，然后乘以1/W,所以可以用滑动窗口
            for (int j = Math.max(0, i - W); j < i; j++) {
                if (j >= K) break;
                dp[i] += dp[j] * 1/W;
            }
        }
        
        double res = 0;
        for (int i = K; i <= N; i++) {
            res += dp[i];
        }
        
        return res;
    }

    public double new21Game(int N, int K, int W) {
        if (K == 0 || N >= K + W) return 1;
        double[] dp = new double[N + 1];
        
        dp[0] = 1;
        double wsum = 1, res = 0;
        
        for (int i = 1; i <= N; i++) {
            //到达i这一步的概率等于这个之前[i-w-1, i-1]窗口的和 * 1/w(当前这一步的概率)
            dp[i] = wsum / W;
            
            if (i < K) {
                wsum += dp[i];
            } else {
                res += dp[i];
            }
            
            if (i >= W) {
                wsum -= dp[i - W];
            }
        }
        
        return res;
    }
}