package graph;
import java.util.*;

class LC286 {
    private int[] dirs = {0, 1, 0, -1, 0};
    
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
        
        int m = rooms.length, n = rooms[0].length;
        
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    int cord = i * n + j;
                    q.offer(cord);
                }
            }
        }
        
        while (!q.isEmpty()) {
            int p = q.poll();
            int px = p / n, py = p % n;
            
            int d = rooms[px][py];
            
            for (int i = 0; i < 4; i++) {
                int x = px + dirs[i], y = py + dirs[i+1];
                
                if (x < 0 || y < 0 || x >= m || y >= n) {
                    continue;
                }
                
                if (rooms[x][y] == Integer.MAX_VALUE || rooms[x][y] > d + 1) {
                    rooms[x][y] = d + 1;
                    int next = x * n + y;
                    q.offer(next);
                }
            }
        }
    }
}