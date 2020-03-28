package graph;
import java.util.*;

class LC1368 {
    public static void main(String[] args) {
        int[][] grid = {
            {1,2,1},
            {2,2,2},
            {1,1,4}
        };
        
        new LC1368().minCost(grid);
    }

    private int[][] dir = {
        {0, 1},
        {0, -1},
        {1, 0},
        {-1, 0}
    };
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        dist[0][0] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.offer(new int[]{0, 0, 0});
        
        while (!pq.isEmpty()) {
            int[] p = pq.poll();
            
            int x = p[1], y = p[2], cost = p[0];
            
            for (int i = 0; i < dir.length; i++) {
                int nx = x + dir[i][0], ny = y + dir[i][1];
                
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                    continue;
                }
                
                //判断下一个方向的点在当前这个格子能否到达，如果不能则标记下一个格子为cost+1
                int ncost = (grid[x][y] == i + 1) ? cost : cost + 1;
                
                if (dist[nx][ny] > ncost) {
                    dist[nx][ny] = ncost;
                    pq.offer(new int[]{ncost, nx, ny});
                }
            }
        }
        
        return dist[m-1][n-1];
    }

    /**
     * BFS+DFS
     * 1.先从起点开始，顺着格子方向走，将能走到的格子都标记成0，并dfs走过的点都加到bfs的queue中
     * 2.从queue中一层一层poll，下一层的cost就是cost+1
     * 3.用dist[i] = Integer.MAX_VALUE区分当前点是否已经访问过，如果不等于Integer.MAX_VALUE说明已经访问过，就不用再访问了
     */
    public int minCost_BFS_DFS(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        Queue<int[]> q = new LinkedList<>();
        int cost = 0;
        dfs(grid, 0, 0, cost, dist, q);
        
        while (!q.isEmpty()) {
            cost += 1;
            
            int size = q.size();
            for (int i = size; i > 0; i--) {
                int[] p = q.poll();
                for (int[] d : dir) {
                    dfs(grid, p[0] + d[0], p[1] + d[1], cost, dist, q);
                }
            }
        }
        
        return dist[m-1][n-1];
    }
    
    private void dfs(int[][] grid, int x, int y, int cost, int[][] dist, Queue<int[]> q) {
        int m = grid.length, n = grid[0].length;
        
        if (x < 0 || y < 0 || x >= m || y >= n || dist[x][y] != Integer.MAX_VALUE) return;

        dist[x][y] = cost;
        q.offer(new int[]{x, y});
        
        int nextDir = grid[x][y] - 1;
        dfs(grid, x + dir[nextDir][0], y + dir[nextDir][1], cost, dist, q);
    }
}