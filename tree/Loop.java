class Loop {
    public static void main(String[] args) {
        char[][] matrix = {
            {'B','B','B','B','B'},
            {'B','G','G','G','B'},
            {'B','G','B','G','B'},
            {'B','x','G','G','B'},
            {'G','B','B','B','B'}
        };

        new Loop().existRing(matrix);
    }
    private int[][] dirs = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };

    private int lastx = -1, lasty = -1;

    public boolean existRing (char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(matrix, i, j, matrix[i][j], -1, -1, visited)) {
                    System.out.println("TRUE");
                    return true;
                }
            }
        }
        System.out.println("FALSE");
        return false;
    }

    private boolean dfs (char[][] matrix, int startx, int starty, char curr, int prex, int prey, boolean[][] visited) {

        if (visited[startx][starty]) {
            return true;
        }

        visited[startx][starty] = true;
        
        for (int[] d: dirs) {
            int x = startx + d[0], y = starty + d[1];

            if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length || matrix[x][y] != curr || (lastx == x && lasty == y)) continue;
            
            lastx = startx;
            lasty = starty;

            if (dfs(matrix, x, y, matrix[x][y], startx, starty, visited)) return true;
        }

        visited[startx][starty] = false;

        return false;
     }
}