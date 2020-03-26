package graph;

class LC785 {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] visited = new int[n];
        
        for (int i = 0; i < graph.length; i++) {
            if (visited[i] == 0) {
                if (!dfs(graph, i, visited, 1)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean dfs(int[][] graph, int start, int[] visited, int flag) {
        if (visited[start] != 0) return visited[start] == flag;
        
        visited[start] = flag;
        
        for (int nb : graph[start]) {
            if (!dfs(graph, nb, visited, flag * (-1))) {
                return false;
            }
        }
        
        return true;
    }
}