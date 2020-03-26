package graph;

class LC490 {
    private int[] d = {0, 1, 0, -1, 0};
    private boolean arrive = false;
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0) return false;
        
        int m = maze.length, n = maze[0].length;
        
        boolean[][] visited = new boolean[m][n];
        dfs(maze, start, destination, visited);
        return arrive;
    }
    
    private void dfs(int[][] maze, int[] start, int[] dest, boolean[][] visited) {
        if (visited[start[0]][start[1]]) return;
        
        visited[start[0]][start[1]] = true;
        
        for (int i = 0; i < 4; i++) {
            int x = start[0] + d[i], y = start[1] + d[i+1];
            
            //先判断一下当前走一步是否有效，如果无效就不再往该方向继续走了
            if (x < 0 || y < 0 || x >= maze.length || y >= maze[0].length || maze[x][y] == 1 || visited[x][y]) {
                continue;
            }
            
            //走一步有效的情况下，判断是否可以往该方向继续走
            while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                x += d[i];
                y += d[i+1];
            }
            
            //因为出while循环的时候刚好是第一个不满足条件的格子，所以退回一步
            x -= d[i];
            y -= d[i+1];
            
            //出了while循环说明当前方向已经走不下去了，需要换方向了，但也可以在该点停下来，判断一下该点是否是目标点
            if (x == dest[0] && y == dest[1]) {
                arrive = true;
                return;
            }
            
            //如果不是目标点则换一个方向继续走
            dfs(maze, new int[]{x, y}, dest, visited);
        }
    }
}