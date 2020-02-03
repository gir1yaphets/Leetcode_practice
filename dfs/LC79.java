class Solution {
    private int[][] dir = {
        {1, 0},
        {-1, 0},
        {0, -1},
        {0, 1}
    };
    
    public boolean exist(char[][] board, String word) {
        if (board.length == 0) return false;
        
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //dfs
                if (board[i][j] != word.charAt(0)) {
                    continue;
                }
                
                //注意这里index从1开始计，因为index0已经找到了
                if (dfs(board, i, j, word, 1)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(char[][] board, int startx, int starty, String word, int index) {
        
        if (index == word.length()) {
            return true;
        }
        
        char letter = board[startx][starty];
        board[startx][starty] = '#';
        
        for (int[] d : dir) {
            int x = startx + d[0], y = starty + d[1];
            
            if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] != word.charAt(index)) {
                continue;
            }
            
            if (dfs(board, x, y, word, index + 1)) {
                return true;
            }
        }
        
        board[startx][starty] = letter;
        
        
        return false;
    }
}
