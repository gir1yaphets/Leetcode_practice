class Solution {
    private int[][] dirs = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
    
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix.length == 0) return res;
        
        int m = matrix.length, n = matrix[0].length;
        int[][] vis = new int[m][n];
        
        for (int j = 0; j < n; j++) {
            dfs(matrix, 0, j, vis, 1);
            dfs(matrix, m - 1, j, vis, 2);
        }
        
        //注意 这里i不能从1~m-1 因为是两个不同的 在确认pac的时候并没有确认atl
        for (int i = 0; i < m; i++) {
            dfs(matrix, i, 0, vis, 1);
            dfs(matrix, i, n - 1, vis, 2);
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (vis[i][j] == 3) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        
        return res;
    }
    
    private void dfs(int[][] matrix, int startx, int starty, int[][] vis, int flag) {
        vis[startx][starty] |= flag;
        
        for (int[] d : dirs) {
            int x = startx + d[0], y = starty + d[1];
            
            if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length || matrix[startx][starty] > matrix[x][y] || (vis[x][y] & flag) != 0) {
                continue;
            }
            
            dfs(matrix, x, y, vis, flag);
        }
    }
    
    
}