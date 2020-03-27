package graph;
import java.util.*;
class LC417 {
    private List<List<Integer>> res = new ArrayList<>(); 
    
    private int[] d = {0, 1, 0, -1, 0};
    
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return res;
        
        int m = matrix.length, n = matrix[0].length;
        int[][] mask = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            dfs(matrix, i, 0, mask, 1);
            dfs(matrix, i, n-1, mask, 2);
        }
        
        for (int j = 0; j < n; j++) {
            dfs(matrix, 0, j, mask, 1);
            dfs(matrix, m-1, j, mask, 2);
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                List<Integer> item = new ArrayList<>();
                if (mask[i][j] == 3) {
                    item.addAll(Arrays.asList(i, j));
                    res.add(item);
                }
            }
        }
        
        return res;
    }
    
    private void dfs(int[][] matrix, int startx, int starty, int[][] mask, int flag) {
        //注意这里判断某一位是否位1 不能(mask[startx][starty] & flag) == 1
        if ((mask[startx][starty] & flag) == flag) {
            return;
        }
        
        mask[startx][starty] |= flag;
        
        for (int i = 0; i < 4; i++) {
            int x = startx + d[i], y = starty + d[i+1];
            
            if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length || matrix[x][y] < matrix[startx][starty]) {
                continue;
            }
            
            dfs(matrix, x, y, mask, flag);
        }
    }
}