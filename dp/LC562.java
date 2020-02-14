class LC562 {
    public int longestLine(int[][] M) {
        if (M == null || M.length == 0) return 0;
        
        int m = M.length, n = M[0].length;
        //正常情况只需要dp[m+1][n+1][4]从而避开i-1<0的情况 但是这题判断反对角线的时候还依赖于右边的一列，所以n+2
        //这道题之所以能依赖于右边一列是因为该元素在当前元素的上一行，所以在判断行的时候已经更新过该点了
        int dp[][][] = new int[m + 1][n + 2][4];
        int max = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1) {
                    //row
                    dp[i+1][j+1][0] = dp[i+1][j][0] + 1;
                    max = Math.max(max, dp[i+1][j+1][0]);
                    
                    //line
                    dp[i+1][j+1][1] = dp[i][j+1][1] + 1;
                    max = Math.max(max, dp[i+1][j+1][1]);
                    
                    //diagnal
                    dp[i+1][j+1][2] = dp[i][j][2] + 1;
                    max = Math.max(max, dp[i+1][j+1][2]);
                    
                    //anti-diagnal
                    dp[i+1][j+1][3] = dp[i][j+2][3] + 1;
                    max = Math.max(max, dp[i+1][j+1][3]);
                }
            }
        }
        
        return max;
    }
}