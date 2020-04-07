class LC44 {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        
        dp[0][0] = true;
        
        for (int i = 1; i <= n; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i-1];
            }
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i-1), pc = p.charAt(j-1);
                if (sc == pc || pc == '?') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (pc == '*') {
                    /**
                     * pc == '*'时候有两种情况
                     * 1.*代表空字符: s = aa p = aa* 这时候只需要传递dp[i][j-1]的结果，即*前面的如果能和当前i长度的s匹配那么*代表空字符也能匹配
                     * 2.*代表一个或多个字符 s = abcd p = ab* 这时候传递dp[i-1][j]的结果，即如果ab*能匹配abc的话，ab*也能匹配abcd
                     */
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }
        
        return dp[m][n];
    }
}