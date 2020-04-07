import java.util.*;
class LC115 {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m+1][n+1];
        
        dp[0][0] = 1;
        
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }
        
        /**
         * s[i] == t[j]: 1.用s[i]匹配t[j] + 不用s[i]匹配t[j]: dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
         */
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        
        return dp[m][n];
    }

    public int numDistinct_memoDfs(String s, String t) {
        int[][] memo = new int[s.length() + 1][t.length() + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dfs(s, t, 0, 0, memo);
    }
    
    private int dfs(String s, String t, int is, int it, int[][] memo) {
        if (it == t.length()) {
            memo[is][it] = 1;
            return 1;
        }
        
        if (is == s.length()) {
            memo[is][it] = 0;
            return 0;
        }
        
        if (memo[is][it] != -1) return memo[is][it];
        
        if (s.charAt(is) == t.charAt(it)) {
            //取s的第is位 + 不取s的第is位
            memo[is][it] = dfs(s, t, is + 1, it + 1, memo) + dfs(s, t, is + 1, it, memo);
        } else {
            memo[is][it] = dfs(s, t, is + 1, it, memo);
        }
        
        return memo[is][it];
    }
}