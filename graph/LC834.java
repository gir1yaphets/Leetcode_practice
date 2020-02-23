class Solution {
    private Map<Integer, List<Integer>> g = new HashMap<>();
    //count[i]: 以i为root的子树有多少个节点，包含i本身
    private int[] count;

    //dist[i]: 以i为root的所有子树的路径和
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
     * post order统计count[i]和dist[i],主要为了得到以0为root的到各个子节点的路径和
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
    
    /**
     * preorder统计每个节点到其他所有节点的路径和
     */
    private void calcDist2All(int root, int pre, int N) {
        for (int child : g.get(root)) {
            if (child == pre) continue;
            
            //有以child为root的节点有count[child]个
            //那么有count[child]个节点距离child节点的距离=这些节点到root节点的距离减少1，
            //有N-count[child]个节点距离child节点的距离=这些节点到root节点距离增加1
            dist[child] = (dist[root] - count[child]) + (N - count[child]);
            
            calcDist2All(child, root, N);
        }
    }
}