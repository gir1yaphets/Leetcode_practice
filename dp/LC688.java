class LC688 {
    private int[][] dir = {
        {1, 2},
        {1, -2},
        {-1, 2},
        {-1, -2},
        {2, 1},
        {2, -1},
        {-2, 1},
        {-2, -1}
    };
    public double knightProbability(int N, int K, int r, int c) {
        double[][][] dp = new double[K + 1][N][N];
        
        dp[0][r][c] = 1;
        
        for (int k = 1; k <= K; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int[] d : dir) {
                        if (i + d[0] < 0 || i + d[0] >= N || j + d[1] < 0 || j + d[1] >= N) continue;
                        
                        dp[k][i+d[0]][j+d[1]] += dp[k-1][i][j];
                    }
                }
            }
        }
        
        double sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += dp[K][i][j];
            }
        }
        
        return sum / Math.pow(8, K);
    }
}