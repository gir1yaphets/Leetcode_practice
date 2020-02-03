import java.util.*;
class Solution {
    private int[][] dir = {
        {1, 0},
        {-1, 0},
        {0, 1},
        {0, -1}
    };
    
    /**
     * 先用dfs找到第二个岛的点，然后以第二个岛的点为起点进行bfs计算最小距离
     * @param A
     * @return
     */
    public int shortestBridge(int[][] A) {
        if (A.length == 0) return 0;
        int m = A.length, n = A[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        int ix = 0, iy = 0;
        
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1 && !visited[i][j]) {
                    if (count == 0) {
                        dfs(A, i, j, visited);
                        count++;
                    } else {
                        ix = i;
                        iy = j;
                        
                        int pos = ix * n + iy;
                        q.offer(pos);
                        map.put(pos, 0);
                    }
                } 
            }
        }
        
        while (!q.isEmpty()) {
            int p = q.poll();
            int dist = map.get(p) + 1;
            
            int r = p / n, c = p % n;
            
            for (int[] d : dir) {
                int x = r + d[0], y = c + d[1];
                int next = x * n + y;
                
                if (x < 0 || y < 0 || x >= m || y >= n || map.containsKey(next) || (A[r][c] == 1 && A[x][y] == 1)) {
                    continue;
                }
                
                if (A[x][y] == 1) {
                    return dist - 1;
                }
                
                q.offer(next);
                map.put(next, dist);
            }
        }
        
        return 0;
    }
    
    private void dfs(int[][] A, int startx, int starty, boolean[][] visited) {
        visited[startx][starty] = true;
        
        for (int[] d : dir) {
            int x = startx + d[0], y = starty + d[1];
            if (x < 0 || y < 0 || x >= A.length || y >= A[0].length || A[x][y] == 0 || visited[x][y]) {
                continue;
            }
            
            dfs(A, x, y, visited);
        }
    }
}