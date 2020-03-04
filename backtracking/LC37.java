class LC37 {
    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }
    
    private boolean backtrack(char[][] board, int r, int c) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') continue;
                
                for (char k = '1'; k <= '9'; k++) {
                    if (isValid(board, i, j, k)) {
                        board[i][j] = k;
                        
                        if (backtrack(board, i, j)) {
                            return true;
                        }
                        
                        board[i][j] = '.';
                    }
                }
                
                return false;
            }
        }
        
        return true;
    }
    
    private boolean isValid(char[][] board, int r, int c, char num) {
        int blockRow = (r / 3) * 3, blockCol = (c / 3) * 3; //算出每一个3*3块左上角的坐标
        
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == num || board[i][c] == num || board[blockRow + i / 3][blockCol + i % 3] == num) {
                return false;
            }
        }
        
        return true;
    }
}