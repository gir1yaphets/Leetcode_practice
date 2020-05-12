public class LC516 {
    /**
     * bbbab
        i = 4, j = 5 x dp[4][4] = 1
        i = 3, j = 4   a != b dp[3][4] = 1
        i = 2, j = 3   b != a dp[2][3] = 1
        i = 2, j = 4   b == b dp[2][4] == dp[3][3] + 2 = 3
        i = 1, j = 2   b == b dp[1][2] = dp[2][1] + 2 = 2
        i = 1, j = 3   b != a dp[1][3] = max(dp[2][3], dp[1][2]) = 2
        i = 1, j = 4   b == b dp[1][4] = dp[2][3] + 2 = 3
        i = 0, j = 4   b == b dp[0][4] = dp[1][3] + 2 = 4
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        
        //因为运算过程涉及到i+1,所以让i倒着走，j正着走，从小到大，当j计算到大的数时比如1~6 那么这个时候1~2,1~3,1~4,1~5都计算过了
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        
        return dp[0][n-1];
    }
}