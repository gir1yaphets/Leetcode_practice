import java.util.*;

class LC505 {
    private int[][] dir = {
        {-1, 0},
        {1, 0},
        {0, -1},
        {0, 1}
    };
    
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        Queue<int[]> q = new LinkedList<>();
        
        int m = maze.length, n = maze[0].length;
        int[][] dist = new int[m][n];
        
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }
            
        q.offer(start);
        dist[start[0]][start[1]] = 0;
        
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int dis = dist[p[0]][p[1]];
            
            for (int[] d : dir) {
                int count = 0;
                
                int x = p[0] + d[0];
                int y = p[1] + d[1];
                while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
                    x += d[0];
                    y += d[1];
                    
                    count++;
                }
                
                x = x - d[0];
                y = y - d[1];
                
                if (dist[x][y] == -1 || dist[x][y] > count + dist[p[0]][p[1]]) {
                    q.offer(new int[]{x, y});
                    dist[x][y] = dis + count;
                }
            }
        }
        
        return dist[destination[0]][destination[1]] == -1 ? -1 : dist[destination[0]][destination[1]];
    }
}