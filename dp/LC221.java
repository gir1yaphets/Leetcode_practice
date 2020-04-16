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

    /**
     * 通用方法: 枚举i,j每一行每一列，然后枚举k表示以i，j为起点的k为边长的矩形
     * dp[i][j]统计i*j矩形的和，这样避免每次都需要对边长为k的矩形再check是否都是1
     */
    public int maximalSquare_common(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m+1][n+1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = matrix[i-1][j-1] - '0' + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1];
            }
        }
        
        int max = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = Math.min(m-i, n-j); k > 0; k--) {
                    int sum = dp[i+k][j+k] - dp[i][j+k] - dp[i+k][j] + dp[i][j];
                    
                    if (sum == k * k) {
                        max = Math.max(sum, max);
                        break;
                    }
                }
            }
        }
        
        return max;
    }
}