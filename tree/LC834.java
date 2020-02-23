import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LC834 {
    private Map<Integer, List<Integer>> g = new HashMap<>();
    //count[i]: 以i为根节点的子树总共的节点个数，包括根节点的个数，即子节点个数+1
    private int[] count;
    //dist[i]:以i为根节点的子树到其他所有节点的距离和
    private int[] dist;
    
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        count = new int[N]; 
        dist = new int[N];
        
        buildGraph(N, edges);
        calcDist2Children(0, -1);
        calcDist2All(0, -1, N);
        
        return dist;
    }
    
    private void buildGraph(int N, int[][] edges) {
        for (int i = 0; i < N; i++) {
            g.put(i, new ArrayList<>());
            
        }
        
        for (int[] e : edges) {
            g.get(e[0]).add(e[1]);
            g.get(e[1]).add(e[0]);
        }
    }
    
    /**
     * 先计算出每个节点到其子节点的距离总和 从而先得到以0个为根节点到其所有节点的距离总和
     */
    private void calcDist2Children(int root, int pre) {
        for (int child : g.get(root)) {
            if (child == pre) continue;
            
            calcDist2Children(child, root);
            
            count[root] += count[child];
            dist[root] += dist[child] + count[child];
        }
        
        count[root] += 1;
    }
    
    private void calcDist2All(int root, int pre, int N) {
        for (int child : g.get(root)) {
            if (child == pre) continue;
            
            //有count[child]离根节点比之前近了1， 有N-count[child]个节点比之前远了1
            dist[child] = (dist[root] - count[child]) + (N - count[child]);
            
            calcDist2All(child, root, N);
        }
    }
}