import java.util.*;

/**
 * 从任意一点出发，走到最远的点，该点一定在最长的路径上(don"t know why...)
 */
class LC1245 {
    private Map<Integer, List<Integer>> g = new HashMap<>();
    
    public int treeDiameter(int[][] edges) {
        int n = edges.length;
        if (n < 0) return 0;
        
        buildGraph(edges);
        
        int farestNode = bfs(n, 0).get(0);
        return bfs(n, farestNode).get(1);
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
    
    private List<Integer> bfs(int n, int start) {
        int[] distance = new int[n + 1];
        Arrays.fill(distance, -1);
        
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        distance[start] = 0;
        
        while (!q.isEmpty()) {
            int v = q.poll();
            
            if (!g.containsKey(v)) continue;
            
            for (int nb : g.get(v)) {
                if (distance[nb] == -1) {
                    q.offer(nb);
                    distance[nb] = distance[v] + 1;
                }
            }
        }
        
        int p = 0;
        int max = 0;
        
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] > max) {
                max = distance[i];
                p = i;
            }
        }
        
        List<Integer> res = new ArrayList<>();
        res.add(p);
        res.add(max);
        
        return res;
    }
}