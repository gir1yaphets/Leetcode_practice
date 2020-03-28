package graph;
import java.util.*;
class LC1334 {
    private Map<Integer, List<int[]>> g = new HashMap<>();
    
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        buildGraph(edges);
        int min = n, res = 0;
        for (int i = 0; i < n; i++) {
            int[] cost = dij(n, i, distanceThreshold);
            
            int reachable = 0;
            
            for (int c : cost) {
                if (c <= distanceThreshold) {
                    reachable += 1;
                }
            }
            
            if (reachable <= min) {
                min = reachable;
                res = i;
            }
        }
        
        return res;
    }
    
    private int[] dij(int n, int v, int ts) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        dist[v] = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> dist[o1] - dist[o2]);
        pq.offer(v);
        
        while (!pq.isEmpty()) {
            int p = pq.poll();
            
            if (g.get(p) != null) {
                for (int[] nb : g.get(p)) {
                    int target = nb[0], cost = nb[1];
                    if (dist[target] > dist[p] + cost && dist[p] + cost <= ts) {
                        dist[target] = dist[p] + cost;
                        pq.offer(target);
                    }
                }
            }
        }
        
        return dist;
    }
    
    private void buildGraph(int[][] edges) {
        for (int[] e : edges) {
            g.putIfAbsent(e[0], new ArrayList<>());
            g.putIfAbsent(e[1], new ArrayList<>());
            
            g.get(e[0]).add(new int[]{e[1], e[2]});
            g.get(e[1]).add(new int[]{e[0], e[2]});
        }
    }
}