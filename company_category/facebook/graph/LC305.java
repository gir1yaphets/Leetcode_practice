package graph;
import java.util.*;

/**
 * Time(O(n)) n is the length of positions
 */
class LC305 {
    private int[] dir = {0, 1, 0, -1, 0};
    private int islands = 0;
    private Map<Integer, Integer> map = new HashMap<>();
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] grid = new int[m][n];
        
        UF uf = new UF(m * n);
        List<Integer> res = new ArrayList<>();
        
        for (int[] p : positions) {
            res.add(checkIslands(grid, p, uf));
        }
        
        return res;
    }
    
    private int checkIslands(int[][] grid, int[] pos, UF uf) {
        int m = grid.length, n = grid[0].length;
        
        int ix = pos[0], iy = pos[1];
        if (grid[ix][iy] == 1) {
            return map.get(islands);
        }
        
        uf.size += 1;
        grid[ix][iy] = 1;
        
        for (int i = 0; i < 4; i++) {
            int x = ix + dir[i], y = iy + dir[i+1];
            
            if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] != 1) continue;
            
            int p1 = ix * n + iy;
            int p2 = x * n + y;
            
            uf.union(p1, p2);
        }
        
        map.put(islands, uf.size);
        
        return uf.size;
    }
    
    class UF {
        int size = 0;
        int[] root;
        int[] rank;
        
        public UF(int n) {
            root = new int[n];
            rank = new int[n];
            
            for (int i = 0; i < n; i++) {
                root[i] = i;
            }
        }
        
        public int find(int x) {
            if (x != root[x]) {
                root[x] = find(root[x]);
            }
            
            return root[x];
        }
        
        public void union(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            
            if (rx != ry) {
                if (rank[rx] < rank[ry]) {
                    root[rx] = ry;
                } else {
                    root[ry] = rx;
                }
                
                if (rank[rx] == rank[ry]) {
                    rank[rx] += 1;
                }
                
                size -= 1;
            }
        }
    }
}