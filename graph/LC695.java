class Solution {
    private int[][] dir = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
    
    private int max = 0;
    
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //dfs
                if (grid[i][j] == 1) {
                    max = Math.max(dfs(grid, i, j), max);
                }
            }
        }
        
        return max;
    }
    
    private int dfs(int[][] grid, int startx, int starty) {
        grid[startx][starty] = 0;
        //一位所有进入dfs的点都是grid[i][j] == 1，所以进来以后的起点就是1，然后加上它四周进行dfs的点作为结果，返回给上一层
        int count = 1;
        
        for (int[] d : dir) {
            int x = startx + d[0], y = starty + d[1];
            
            if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == 0) {
                continue;
            }
            
            count += dfs(grid, x, y);
        }
        
        return count;
    }
}