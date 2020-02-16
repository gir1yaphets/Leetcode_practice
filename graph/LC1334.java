class LC1334 {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] dp = new int[n][n];
        
        //如果两个点非连通，为了后面能将该两点间距离更新为经过某个中间点的距离，所以这里要初始化为最大值
        for (int[] row : dp) {
            Arrays.fill(row, 10001);
        }
        
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }
        
        for (int[] e : edges) {
            dp[e[0]][e[1]] = dp[e[1]][e[0]] = e[2];
        }
        
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        
        int min = 10001;
        int res = 0;
        
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (dp[i][j] <= distanceThreshold) {
                    count += 1;
                }
            }
            
            if (count <= min) {
                min = count;
                res = i;
            }
        }
        
        return res;
    }
    
}