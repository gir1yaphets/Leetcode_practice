import java.util.*;

class LC542 {
    private int[][] dir =  {
        {1, 0},
        {-1, 0},
        {0, -1},
        {0, 1}
    };
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix.length == 0) return matrix;
        
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    int pos = i * n + j;
                    q.offer(pos);
                    map.put(pos, 0);
                }
            }
        }
        
        while (!q.isEmpty()) {
            int p = q.poll();
            int dist = map.get(p) + 1;
            
            int r = p / n, c = p % n;
            
            for (int[] d : dir) {
                int x = r + d[0], y = c + d[1];
                int nextPos = x * n + y;
                
                if (x < 0 || y < 0 || x >= m || y >= n || matrix[x][y] == 0 || (map.containsKey(nextPos) && map.get(nextPos) < dist)) {
                    continue;
                }
                
                q.offer(nextPos);
                map.put(nextPos, dist);
                matrix[x][y] = dist;
            }
        }
        
        return matrix;
    }

    public int[][] updateMatrix_dp(int[][] matrix) {
        if (matrix.length == 0) return matrix;
        
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        
        int max = m * n;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    int up = i > 0 ? dp[i-1][j] : max;
                    int left = j > 0 ? dp[i][j-1] : max;
                    
                    dp[i][j] = Math.min(left, up) + 1;
                }
            }
        }
        
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] != 0) {
                    int down = i < m - 1 ? dp[i+1][j] : max;
                    int right = j < n - 1 ? dp[i][j+1] : max;
                    
                    int min = Math.min(down, right) + 1;
                    dp[i][j] = Math.min(dp[i][j], min);
                }
            }
        }
        
        return dp;
    }
}