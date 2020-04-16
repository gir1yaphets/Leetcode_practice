/**
 * 和221题一样，dp[i][j] = min(左, 上，左上) + 1 既代表了当前点(i,j)能构成的正方形的最大边长，又代表了到(i,j)点新增加了几个正方形
 */
class LC1277 {
    public int countSquares(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        
        int[][] dp = new int[m][n];
        int res = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) continue;
                
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
                
                res += dp[i][j];
            }
        }
        
        return res;
    }
}