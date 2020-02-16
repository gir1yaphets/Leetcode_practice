class LC1334 {
    public int findTheCity_Floyed(int n, int[][] edges, int distanceThreshold) {
        int[][] dp = new int[n][n];
        
        //如果两个点非连通，为了后面能将该两点间距离更新为经过某个中间点的距离，所以这里要初始化为最大值
        for (int[] row : dp) {
            Arrays.fill(row, 10001);
        }
        
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }
        
        for (int[] e : edges) {
            dp[e[0]][e[1]] = dp[e[1]][e[0]] = e[2];
        }
        
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        
        int min = 10001;
        int res = 0;
        
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (dp[i][j] <= distanceThreshold) {
                    count += 1;
                }
            }
            
            if (count <= min) {
                min = count;
                res = i;
            }
        }
        
        return res;
    }
    
    private Map<Integer, List<int[]>> g = new HashMap<>();
    
    public int findTheCity_dij(int n, int[][] edges, int distanceThreshold) {
        buildGraph(edges);
        
        int res = 0;
        int min = n;
        
        for (int i = 0; i < n; i++) {
            int[] costs = dij(n, i);
            
            int count = 0;
            
            for (int cost : costs) {
                if (cost <= distanceThreshold) {
                    count += 1;
                }
            }
        
            if (count <= min) {
                min = count;
                res = i;
            }
        }
        
        return res;
    }
    
    private void buildGraph(int[][] edges) {
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            
            List<int[]> ulist = g.getOrDefault(u, new ArrayList<>());
            List<int[]> vlist = g.getOrDefault(v, new ArrayList<>());
            
            ulist.add(new int[]{v, w});
            vlist.add(new int[]{u, w});
            
            g.put(u, ulist);
            g.put(v, vlist);
        }
    }
    
    private int[] dij(int n, int src) {
        int[] cost = new int[n];
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> cost[o1] - cost[o2]);
        
        Arrays.fill(cost, 10001);
        cost[src] = 0;
        pq.offer(src);
        
        while (!pq.isEmpty()) {
            int v = pq.poll();
            visited.add(v);
            
            List<int[]> nbList = g.get(v);
            if (nbList != null) {
                for (int[] nb : nbList) {
                    int u = nb[0], w = nb[1];
                    
                    if (visited.contains(u)) {
                        continue;
                    }
                    
                    if (cost[u] > w + cost[v]) {
                        cost[u] = w + cost[v];
                        pq.offer(u);
                    }
                }
            }
        }
        
        return cost;
    }
}