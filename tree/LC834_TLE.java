import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
    private Map<Integer, List<Integer>> g = new HashMap<>();
    
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        buildGraph(edges);
        
        int[] res = new int[N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int d = getDistance(i, j);
                res[i] += getDistance(i, j);
                // System.out.println("i = " + i + " j = " + j + " d = " + d);
            }
        }
        
        return res;
    }
    
    private void buildGraph(int[][] edges) {
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            
            g.putIfAbsent(u, new ArrayList<>());
            g.putIfAbsent(v, new ArrayList<>());
            
            g.get(u).add(v);
            g.get(v).add(u);
        }
    }
    
    private int getDistance(int p1, int p2) {
        if (p1 == p2) return 0;
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(p1);
        
        Map<Integer, Integer> dist = new HashMap<>();
        dist.put(p1, 0);
        
        while (!q.isEmpty()) {
            int v = q.poll();
            int d = dist.get(v);
            
            if (g.get(v) == null) {
                continue;
            }
            
            for (int nb : g.get(v)) {
                if (nb == p2) {
                    return d + 1;
                }
                
                if (dist.containsKey(nb)) {
                    continue;
                }
                
                dist.put(nb, d + 1);
                q.offer(nb);
            }
        }
        
        return -1;
    }
}