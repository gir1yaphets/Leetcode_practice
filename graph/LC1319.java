class LC1319 {
    public int makeConnected(int n, int[][] connections) {
        if (n <= 0 || connections == null || connections.length == 0) return -1;
        
        int[] roots = new int[n];
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
        
        int redundant = 0;
        
        for (int[] conn : connections) {
            int x = conn[0], y = conn[1];
            
            visited[x] = true;
            visited[y] = true;
            
            int rootx = find(x, roots);
            int rooty = find(y, roots);
            
            if (rootx == rooty) {
                redundant += 1;
            } else {
                roots[rooty] = rootx;
            }
        }
        
        int isolated = 0;
        for (int i = 0; i < n; i++) {
            if (roots[i] == i) {
                isolated += 1;
            }
        }
        
        return redundant >= isolated -1 ? isolated - 1 : -1;
    }
    
    private int find(int x, int[] roots) {
        if (x != roots[x]) {
            roots[x] = find(roots[x], roots);
        }
        
        return roots[x];
    }
}