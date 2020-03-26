package graph;
import java.util.*;

class LC505 {
    private int[] d = {0, 1, 0, -1, 0};
    
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        
        int[][] dis = new int[m][n];
        for (int[] row : dis) {
            Arrays.fill(row, -1);
        }
        dis[start[0]][start[1]] = 0;
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        
        while (!q.isEmpty()) {
            int[] p = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int x = p[0] + d[i];
                int y = p[1] + d[i+1];
                
                int step = 0;
                
                while (x >= 0 && y >= 0 && x < m && y < n && maze[x][y] == 0) {
                    x += d[i];
                    y += d[i+1];
                    
                    step += 1;
                }
                
                x -= d[i];
                y -= d[i+1];
                
                //错误: 不能遇到destination就直接return，需要找最短距离
                // dis[x][y] = dis[p[0]][p[1]] + step;
                
                // if (x == destination[0] && y == destination[1]) {
                //     return dis[x][y];
                // }
                
                if (dis[x][y] == -1 || dis[p[0]][p[1]] + step < dis[x][y]) {
                    dis[x][y] = dis[p[0]][p[1]] + step;
                    q.offer(new int[]{x, y});
                }
            }
        }
        
        return dis[destination[0]][destination[1]] == -1 ? -1 : dis[destination[0]][destination[1]];
    }
}