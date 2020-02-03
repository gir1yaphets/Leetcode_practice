class Solution {
    int[][] directions = {
            {0, 1},
            {0, -1},
            {-1, 0},
            {1, 0}
    };
    
    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] != '0') {
                    count++;
                    dfs(grid, i, j, visited);
                }
            }
        }
        
        return count;
    }
    
    private void dfs(char[][] grid, int startx, int starty, boolean[][] visited) {
        for (int[] d : directions) {
            int x = startx + d[0], y = starty + d[1];
            
            if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == '0' || visited[x][y]) {
                continue;
            }
            
            grid[startx][starty] = '0';
            visited[startx][starty] = true;
            dfs(grid, x, y, visited);
        }
    }

    /**
     * 这道题不需要visited数组，每次开始一个起点，将其标记成‘0’ 然后遇到‘0’不进行dfs 这样就可以保证不会走到之前访问过的点
     * 因为之前访问过的‘1’已经被改成'0‘了
     * @param grid
     * @return
     */
    public int numIslands_novisited(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != '0') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        
        return count;
    }
    
    private void dfs(char[][] grid, int startx, int starty) {
        grid[startx][starty] = '0';
        for (int[] d : directions) {
            int x = startx + d[0], y = starty + d[1];
            
            if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == '0') {
                continue;
            }
            
            dfs(grid, x, y);
        }
    }

    /**
     *  Union find solution
     */
    public int numIslands_uf(char[][] grid) {
        if (grid.length == 0) return 0;
        
        int m = grid.length, n = grid[0].length;
        int count = 0;
        
        int[] root = new int[m * n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    int p = i * n + j;
                    root[p] = p;
                    
                    //只统计初始1的个数
                    count += 1;
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                
                for (int[] d : dir) {
                    int x = i + d[0], y = j + d[1];
                    
                    if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == '0') {
                        continue;
                    }
                    
                    int index1 = i * n + j, index2 = x * n + y;
                    
                    int rootx = find(index1, root);
                    int rooty = find(index2, root);
                    
                    if (rootx != rooty) {
                        root[rooty] = rootx;
                        count--;
                    }
                }
            }
        }
        
        return count;
    }
    
    private int find(int x, int[] root) {
        if (x != root[x]) {
            root[x] = find(root[x], root);
        }
        
        return root[x];
    }
}