class LC221 {
    /**
     * 当前最大矩阵能否多扩充一维取决于当前点的左边，上边，左上三个点为右下角构成的正方形个数的最小值+1
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        
        int max = 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') continue;
                
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
                
                max = Math.max(max, dp[i][j]);
            }
        }
        
        return max * max;
    }
}