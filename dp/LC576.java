class LC576 {
    private int dir[] = {0, 1, 0, -1, 0};
    
    public int findPaths(int m, int n, int N, int i, int j) {
        if (N <= 0) return 0;
        
        final int MOD = 1000000007;
        int[][] dp = new int[m][n];
        dp[i][j] = 1;
        int result = 0;
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        for (int step = 0; step < N; step++) {
            int[][] next = new int[m][n];
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    for (int[] d : dirs) {
                        int nr = r + d[0];
                        int nc = c + d[1];
                        if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                            result = (result + dp[r][c]) % MOD;
                        }
                        else {
                            next[nr][nc] = (next[nr][nc] + dp[r][c]) % MOD;
                        }
                    }
                }
            }
            dp = next;
        }
        
        return result;
        
    }
}