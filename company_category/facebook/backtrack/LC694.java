package backtrack;
import java.util.*;

class LC694 {
    private int[][] cor = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
    
    private String[] dir = {"d", "u", "r", "l"};
    
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int m = grid.length, n = grid[0].length;
        Set<String> res = new HashSet<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb);
                    res.add(sb.toString());
                }
            }
        }
        
        return res.size();
    }
    
    private void dfs(int[][] grid, int x, int y, StringBuilder sb) {
        int m = grid.length, n = grid[0].length;
        
        grid[x][y] = 0;
        
        for (int i = 0; i < cor.length; i++) {
            int nx = x + cor[i][0], ny = y + cor[i][1];
            
            if (nx < 0 || ny < 0 || nx >= m || ny >= n || grid[nx][ny] == 0) {
                continue;
            }
            
            dfs(grid, nx, ny, sb.append(dir[i]));
        }
        
        //注意回退的时候要加一个区分的字母，要不回退以后再找到新的方向有可能和之前的一样
        //[[1,1,0],[0,1,1],[0,0,0],[1,1,1],[0,1,0]]
        sb.append("b");
    }
}