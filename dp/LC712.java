class LC712 {
    public int minimumDeleteSum(String s1, String s2) {
        int[][] memo = new int[s1.length() + 1][s2.length() + 1];
        return dfs(s1, s2, 0, 0, memo);
    }
    
    private int dfs(String s1, String s2, int p1, int p2, int[][] memo) {
        if (memo[p1][p2] != 0) return memo[p1][p2];
        
        if (p1 == s1.length() && p2 == s2.length()) {
            memo[p1][p2] = 0;
            return 0;
        }
        
        int result;
        if (p1 == s1.length()) {
            result = s2.charAt(p2) + dfs(s1, s2, p1, p2 + 1, memo);
        } else if (p2 == s2.length()) {
            result = s1.charAt(p1) + dfs(s1, s2, p1 + 1, p2, memo);
        } else {
            result = s1.charAt(p1) == s2.charAt(p2) ? 
                dfs(s1, s2, p1 + 1, p2 + 1, memo) : Math.min(s2.charAt(p2) + dfs(s1, s2, p1, p2 + 1, memo), s1.charAt(p1) + dfs(s1, s2, p1 + 1, p2, memo));
        }
        
        memo[p1][p2] = result;
        return result;
    }

    public int minimumDeleteSum_dp(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i-1][0] + s1.charAt(i-1);
        }
        
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j-1] + s2.charAt(j-1);
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(s1.charAt(i-1) + dp[i-1][j], s2.charAt(j-1) + dp[i][j-1]);
                }
            }
        }
        
        return dp[m][n];
    }
}