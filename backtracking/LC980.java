class LC980 {
    private int[] d = {0, 1, 0, -1, 0};
    private int zero = 1; //最后走到2的时候会多加一个1 所以zero也要多1和num比较
    private int res = 0;
    
    public int uniquePathsIII(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int startx = 0, starty = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    startx = i;
                    starty = j;
                } else if (grid[i][j] == 0) {
                    zero += 1;
                }
            }
        }
        
        dfs(grid, startx, starty, 0);
        
        return res;
    }
    
    private void dfs(int[][] grid, int r, int c, int num) {
        if (grid[r][c] == 2) {
            if (num == zero) {
                res += 1;
            }

            return;
        }
        
        int org = grid[r][c];
        grid[r][c] = -2;
        
        for (int i = 0; i < 4; i++) {
            int x = r + d[i], y = c + d[i+1];
            
            if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == -1 || grid[x][y] == -2) {
                continue;
            }
            
            dfs(grid, x, y, num + 1);
        }
        
        grid[r][c] = org;
    }
}