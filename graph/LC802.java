class Solution {
    /**
     * 判断有向图是否存在环问题
     * visited[i] = 2,代表i在某一个环中，visited[i] = 1代表i访问过并且不在环中
     * 每次先标记的访问的节点为2，当dfs过程中遇到一个访问过的节点的时候，如果该点等于2那么dfs返回false，并把之前一路上过来的节点都返回false
     * 当该节点在dfs的相邻接点中都没有返回false，则将该节点标记为1
     * @param graph
     * @return
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] visited = new int[n];
        List<Integer> res = new ArrayList<>();
        if (n <= 0) return res;
        
        for (int i = 0; i < n; i++) {
            //dfs
            if (dfs(graph, i, visited)) {
                res.add(i);
            }
        }
        
        return res;
    }
    
    private boolean dfs(int[][] graph, int start, int[] visited) {
        //如果该节点已经被访问过，那么该点不能是2 比如 1->2<-3 当访问到2的时候，2的visited已经被标记称了1
        if (visited[start] != 0) {
            return visited[start] == 1;
        }
        
        visited[start] = 2;
        for (int nb : graph[start]) {
            
            if (!dfs(graph, nb, visited)) {
                return false;
            }
        }
        
        visited[start] = 1;
        
        return true;
    }
}