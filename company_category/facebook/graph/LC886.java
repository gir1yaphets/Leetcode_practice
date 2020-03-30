package graph;
import java.util.*;
class LC886 {
    private Map<Integer, Set<Integer>> g = new HashMap<>();
    
    public boolean possibleBipartition(int N, int[][] dislikes) {
        buildGraph(N, dislikes);
        
        int[] colors = new int[N + 1];
        
        for (int i = 1; i <= N; i++) {
            if (colors[i] == 0 && !dfs(i, 1, colors)) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean dfs(int start, int color, int[] colors) {
        if (colors[start] != 0) {
            return colors[start] == color;
        }
        
        colors[start] = color;
        
        for (int nb : g.get(start)) {
            if (!dfs(nb, -color, colors)) {
                return false;
            }
        }
        
        return true;
    }
    
    private void buildGraph(int N, int[][] dis) {
        for (int i = 1; i <= N; i++) {
            g.put(i, new HashSet<>());
        }
        
        for (int[] d : dis) {
            g.get(d[0]).add(d[1]);
            g.get(d[1]).add(d[0]);
        }
    }
}