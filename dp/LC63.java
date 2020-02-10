class LC63 {
    public int uniquePathsWithObstacles(int[][] ob) {
        if (ob.length == 0 || ob[0].length == 0) return 0;
        int m = ob.length, n = ob[0].length;
        
        int[][] dp = new int[m][n];
        
        dp[m-1][n-1] = 1 - ob[m-1][n-1];
        
        for (int j = n - 2; j >= 0; j--) {
            dp[m-1][j] = (ob[m-1][j] == 1 || dp[m-1][j+1] == 0) ? 0 : 1;
        }
        
        for (int i = m - 2; i >= 0; i--) {
            dp[i][n-1] = (ob[i][n-1] == 1 || dp[i+1][n-1] == 0) ? 0 : 1;
        }
        
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = ob[i][j] == 1 ? 0 : dp[i+1][j] + dp[i][j+1];
            }
        }
        
        return dp[0][0];
    }
}