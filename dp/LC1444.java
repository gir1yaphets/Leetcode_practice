class LC1444 {
    private static final int MOD = 1000000007;
    public int ways(String[] pizza, int k) {
        int m = pizza.length, n = pizza[0].length();
        int[][] presum = new int[m+1][n+1];//presum[i][j]表示从(i,j)~(m-1,n-1)苹果的数量
        int[][][] dp = new int[m][n][k];
        
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                presum[i][j] = (pizza[i].charAt(j) == 'A' ? 1 : 0) + presum[i+1][j] + presum[i][j+1] - presum[i+1][j+1];
            }
        }
        
        return dfs(m, n, 0, 0, k - 1, presum, dp);
    }
    
    /**
     * r,c表示当前起始的行,列的index
     * @param r
     * @param c
     */
    private int dfs(int m, int n, int r, int c, int k, int[][] presum, int[][][] dp) {
        if (presum[r][c] == 0) return 0;
        if (k == 0) return 1;
        
        if (dp[r][c][k] != 0) return dp[r][c][k];
        
        long res = 0;
        for (int i = 1; r + i < m; i++) {
            int nr = r + i;
            if (presum[r][c] - presum[nr][c] > 0) {
                res = (res + dfs(m, n, nr, c, k - 1, presum, dp)) % MOD;
            }
        }
        
        for (int j = 1; c + j < n; j++) {
            int nc = c + j;
            if (presum[r][c] - presum[r][nc] > 0) {
                res = (res + dfs(m, n, r, nc, k - 1, presum, dp)) % MOD;
            }
        }
        
        return dp[r][c][k] = (int) res;
    }
}