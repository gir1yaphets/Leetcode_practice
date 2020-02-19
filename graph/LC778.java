import java.util.*;

class LC778 {
    private int[][] dir = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
    
    public int swimInWater(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int max = grid[0][0];
        boolean[][] visited = new boolean[m][n];
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> grid[o1 / n][o1 % n] - grid[o2 / n][o2 % n]);
        pq.offer(0);
        
        
        while (!pq.isEmpty()) {
            int p = pq.poll();
            
            int r = p / n, c = p % n;
            visited[r][c] = true;
            
            max = Math.max(max, grid[r][c]);
            
            for (int[] d : dir) {
                int x = r + d[0], y = c + d[1];
               
                if (x == m - 1 && y == n - 1) {
                    max = Math.max(max, grid[m-1][n-1]);
                    return max;
                }
                
                
                if (x < 0 || y < 0 || x >= m || y >= n || visited[x][y]) {
                    continue;
                }
                
                pq.offer(x * n + y);
            }
        }
        
        return max;
    }
}