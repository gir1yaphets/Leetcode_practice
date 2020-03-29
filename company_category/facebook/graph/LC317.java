package graph;
import java.util.*;

class LC317 {
    private int[] dir = {0, 1, 0, -1, 0};
    
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        
        int m = grid.length, n = grid[0].length;

        int[][] dist = new int[m][n];
        int[][] reachable = new int[m][n];
        
        int building = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    building += 1;
                    bfs(grid, i, j, dist, reachable);
                }
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //注意可能存在不是能到达所有building的点
                if (grid[i][j] == 0 && reachable[i][j] == building) {
                    min = Math.min(min, dist[i][j]);
                }
            }
        }
 
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    private void bfs(int[][] grid, int r, int c, int[][] dist, int[][] reachable) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        
        dist[r][c] = 0;
        int level = 0;
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int[] p = q.poll();
                int x = p[0], y = p[1];
                //错误做法，如果用dist[x][y]去更新下一层节点的距离会出错，因为dist[x][y]可能包含了之前一层的距离，并只是当前层的距离
                // int d = dist[x][y];
                
                for (int j = 0; j < 4; j++) {
                    int nx = x + dir[j], ny = y + dir[j+1];
                
                    if (nx < 0 || ny < 0 || nx >= m || ny >= n || grid[nx][ny] != 0 || visited[nx][ny]) {
                        continue;
                    }

                    
                    dist[nx][ny] += level + 1;
                    reachable[nx][ny] += 1;
                    
                    visited[nx][ny] = true;

                    q.offer(new int[]{nx, ny});
                }
            }
            
            level += 1;
        }
    }
}