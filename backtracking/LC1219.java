import java.util.*;

class LC1219 {
    private int[][] dir = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
    
    public int getMaximumGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int max = 0;
        Set<Integer> visited = new HashSet<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 || visited.contains(i * n + j)) continue;
                
                max = Math.max(max, dfs(grid, i, j, visited));
            }
        }
        
        return max;
    }
    
    private int dfs(int[][] grid, int startx, int starty, Set<Integer> visited) {
        int count = grid[startx][starty];
        int m = grid.length, n = grid[0].length;
        visited.add(startx * n + starty);
        
        int max = 0;
        for (int[] d : dir) {
            int x = startx + d[0], y = starty + d[1];
            
            if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 0 || visited.contains(x * n + y)) {
                continue;
            }
            
            max = Math.max(dfs(grid, x, y, visited), max);
        }
        
        visited.remove(startx * n + starty);
        
        return count + max;
    }
}