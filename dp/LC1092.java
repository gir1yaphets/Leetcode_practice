class LC1092 {
    /**
     * 1.先求出LCS的dp
     * 2.根据dp的关系找到common和unique
     */
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length(), n = str2.length();
        int[][] dp = new int[m+1][n+1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = str1.charAt(i - 1) == str2.charAt(j - 1) ? dp[i-1][j-1] + 1 : Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        
        int p1 = m, p2 = n;
        StringBuilder sb = new StringBuilder();
        
        while (p1 > 0 || p2 > 0) {
            if (p1 == 0) {
                sb.insert(0, str2.charAt(--p2));
            } else if (p2 == 0) {
                sb.insert(0, str1.charAt(--p1));
            } else if (str1.charAt(p1 - 1) == str2.charAt(p2 - 1)) {
                sb.insert(0, str1.charAt(--p1));
                p2--;
            } else if (dp[p1][p2] == dp[p1 - 1][p2]) {
                //如果dp[p1][p2] == dp[p1-1][p2]说明str1.charAt(p1-1)没有common string增加，这一位是单独的
                sb.insert(0, str1.charAt(--p1));
            } else if (dp[p1][p2] == dp[p1][p2 - 1]) {
                sb.insert(0, str2.charAt(--p2));
            }
        }
        
        return sb.toString();
    }
}