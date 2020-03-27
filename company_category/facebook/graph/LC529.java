package graph;
class LC529 {
    private int[][] dirs = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1},
        {1, 1},
        {1, -1},
        {-1, 1},
        {-1, -1}
    };
    
    public char[][] updateBoard(char[][] board, int[] click) {
        dfs(board, click[0], click[1]);
        
        return board;
    }
    
    private void dfs(char[][] b, int startx, int starty) {
        if (b[startx][starty] == 'B') return;
        
        if (b[startx][starty] == 'M') {
            b[startx][starty] = 'X';
            return;
        } else if (b[startx][starty] == 'E' || b[startx][starty] == 'B') {
            int mineCount = 0;
            
            for (int[] d : dirs) {
                int x = startx + d[0], y = starty + d[1];

                if (x < 0 || y < 0 || x >= b.length || y >= b[0].length) {
                    continue;
                }

                if (b[x][y] == 'M') {
                    mineCount += 1;
                }
            }
            
            if (mineCount == 0) {
                b[startx][starty] = 'B';
                
                for (int[] d : dirs) {
                    int x = startx + d[0], y = starty + d[1];

                    if (x < 0 || y < 0 || x >= b.length || y >= b[0].length) {
                        continue;
                    }

                    dfs(b, x, y);
                }
            } else {
                b[startx][starty] = (char)(mineCount + '0');
            }
        }
    }
}