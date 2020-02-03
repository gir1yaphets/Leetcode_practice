class Solution {
    private int[][] dir = new int[][] {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
    
    /**
     * 因为只要和边联通的点就不能变成X 所以就从边缘的O开始，将连通的O都标记成一个特殊字符#，
     * 这样所有和边缘连通的O都变成了#,那么剩余的O就要变成X
     * @param board
     */
    public void solve(char[][] board) {
        if (board.length == 0) return;
        
        int m = board.length, n = board[0].length;
        
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                dfs(board, 0, j);
            }
        }
        
        for (int i = 1; i < m; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
        }
        
        for (int j = 1; j < n - 1; j++) {
            if (board[m-1][j] == 'O') {
                dfs(board, m-1, j);
            }
        }
        
        for (int i = 1; i < m - 1; i++) {
            if (board[i][n-1] == 'O') {
                dfs(board, i, n-1);
            }
        }
        
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] != '#') {
                    board[i][j] = 'X';
                } else {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    private void dfs(char[][] board, int startx, int starty) {
        for (int[] d : dir) {
            int x = startx + d[0], y = starty + d[1];
            
            if (x <= 0 || y <= 0 || x >= board.length - 1 || y >= board[0].length - 1 || board[x][y] != 'O') continue;
                
            board[x][y] = '#';
            dfs(board, x, y);
        }
    }
}