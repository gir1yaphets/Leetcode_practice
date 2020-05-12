
public class LC1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] memo = new int[text1.length()][text2.length()];
        return dfs(text1, text2, 0, 0, memo);
    }
    
    private int dfs(String s1, String s2, int p1, int p2, int[][] memo) {
        if (p1 == s1.length() || p2 == s2.length()) return 0;
        
        if (memo[p1][p2] != 0) return memo[p1][p2];
        
        int res = 0, max = 0;
        if (s1.charAt(p1) == s2.charAt(p2)) {
            res = 1 + dfs(s1, s2, p1 + 1, p2 + 1, memo);
        } else {
            max = Math.max(dfs(s1, s2, p1 + 1, p2, memo), dfs(s1, s2, p1, p2 + 1, memo));
        }
        res = Math.max(res, max);
        memo[p1][p2] = res;
        
        return res;
    }

    public int longestCommonSubsequence_dp(String text1, String text2) {
        int s1 = text1.length(), s2 = text2.length();
        int[][] dp = new int[s1 + 1][s2 + 1];
        
        for (int i = 1; i <= s1; i++) {
            for (int j = 1; j <= s2; j++) {
                int lcs = 0;
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    lcs = 1 + dp[i - 1][j - 1];
                } else {
                    lcs = Math.max(dp[i-1][j], dp[i][j-1]);
                }
                
                dp[i][j] = lcs;
            }
        }
        
        return dp[s1][s2];
    }
}