class LC1267 {
    public int countServers(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int m = grid.length, n = grid[0].length;
        int[] row = new int[m], col = new int[n];
        int total = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    row[i]++;
                    col[j]++;
                    total++;
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //当这个cell是1 并且该行该列只有一个server的时候 该server不能通信，从总数中减掉
                if (grid[i][j] == 1) {
                    if (row[i] == 1 && col[j] == 1) {
                        total -= 1;
                    }
                }
            }
        }
        
        return total;
    }
}