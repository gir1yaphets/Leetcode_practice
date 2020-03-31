package backtrack;
import java.util.*;

class LC329 {
    private int[] d = {0, 1, 0, -1, 0};
    
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        
        int m = matrix.length, n = matrix[0].length;
        int[][] memo = new int[m][n];
        
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        
        int max = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(matrix, i, j, memo));
            }
        }
        
        return max;
    }
    
    /**
     * memo[x][y]记录从x，y出发的最长路径
     */
    private int dfs(int[][] matrix, int x, int y, int[][] memo) {
        if (memo[x][y] != -1) return memo[x][y];
        
        int m = matrix.length, n = matrix[0].length;
        
        int max = 1;
        
        for (int i = 0; i < 4; i++) {
            int nx = x + d[i], ny = y + d[i+1];
            
            if (nx < 0 || ny < 0 || nx >= m || ny >= n || matrix[nx][ny] <= matrix[x][y]) {
                continue;
            }
            
            //记录从当前层开始能走的最远路径
            int path = 1 + dfs(matrix, nx, ny, memo);
            max = Math.max(max, path);
        }
        
        memo[x][y] = max;
        
        return max;
    }
}