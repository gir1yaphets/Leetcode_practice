import java.util.*;
class LC1091 {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if(grid[0][0]==1 || grid[n-1][n-1]==1) {
            return -1;
        }
        
        int res = 0;
        int[][] dir = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1},
        };
        
        boolean [][] visited = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] p = q.poll();

                if (p[0] == n - 1 && p[1] == n - 1) {
                    return res + 1;
                }

                for (int[] d : dir) {
                    int x = p[0] + d[0], y = p[1] + d[1];
                    if (x < 0 || y < 0 || x >= n || y >= n || visited[x][y] || grid[x][y] == 1) {
                        continue;
                    }

                    visited[x][y] = true;
                    q.offer(new int[]{x, y});
                }
            }
            res += 1;
        }
        
        return -1;
    }
}