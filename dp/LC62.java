class LC62 {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 1;
        }
        
        if (m == 1 || n == 1) {
            return 1;
        }
        
        int[][] dp = new int[m][n];
        
        dp[m-1][n-1] = 0;
        
        for (int j = 0; j < n; j++) {
            dp[m-1][j] = 1;
        }
        
        for (int i = 0; i < m; i++) {
            dp[i][n-1] = 1;
        }
        
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = dp[i+1][j] + dp[i][j+1];
            }
        }
        
        return dp[0][0];
    }

    public int uniquePaths_top2bottom(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        
        return dp[m-1][n-1];
    }
}