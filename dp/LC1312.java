class LC1312 {
    public int minInsertions(String s) {
        int n = s.length();
        int[][] memo = new int[n][n];
        
        return dfs(s, 0, s.length() - 1, memo);
    }
    
    private int dfs(String s, int l, int r, int[][] memo) {
        if (l > r) return 0;
        
        if (memo[l][r] != 0) return memo[l][r];
        
        if (s.charAt(l) == s.charAt(r)) return dfs(s, l + 1, r - 1, memo);
        
        int count = 1 + Math.min(dfs(s, l + 1, r, memo), dfs(s, l, r - 1, memo));
        
        return memo[l][r] = count;
    }

    /**
     * 解法类似于LC516
     */
    public int minInsertions_dp_bottom_up(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        
        return dp[0][n-1];
    }

    public int minInsertions_dp(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        
        //长度又小到大，先从头到尾部，把长度是2的结果算出来，然后扩展到长度为n
        for (int l = 2; l <= n; l++) {
            for (int i = 0, j = l - 1; j < n; i++, j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        
        return dp[0][n-1];
    }
}