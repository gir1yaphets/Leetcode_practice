import java.util.*;

class LC1135 {
    public int minimumCost_uf(int N, int[][] connections) {
        int[] root = new int[N];
        int count = N;
        int cost = 0;
        for (int i = 0; i < N; i++) {
            root[i] = i;
        }
        
        Arrays.sort(connections, (o1, o2) -> o1[2] - o2[2]);
        for (int[] conn : connections) {
            int rootx = find(root, conn[0] - 1);
            int rooty = find(root, conn[1] - 1);
            
            if (rootx != rooty) {
                root[rooty] = rootx;
                count -= 1;
                cost += conn[2];
            }
            
            if (count == 1) {
                break;
            }
            
        }
        
        return count == 1 ? cost : -1;
    }

    private int find(int[] root, int x) {
        if (root[x] != x) {
            root[x] = find(root, root[x]);
        }
        
        return root[x];
    }


    /**
     * bfs
     * @param N
     * @param connections
     * @return
     */
    public int minimumCost(int N, int[][] connections) {
        Map<Integer, List<int[]>> g = new HashMap<>();
        buildGraph(connections, g);
        return bfs(N, g);
    }
    
    private void buildGraph(int[][] connections, Map<Integer, List<int[]>> g) {
        for (int[] conn : connections) {
            int v1 = conn[0], v2 = conn[1], cost = conn[2];
            List<int[]> neigh1 = g.getOrDefault(v1, new ArrayList<>());
            List<int[]> neigh2 = g.getOrDefault(v2, new ArrayList<>());
            
            neigh1.add(new int[]{v2, cost});
            neigh2.add(new int[]{v1, cost});
            
            g.put(v1, neigh1);
            g.put(v2, neigh2);
        }
    }
    
    private int bfs(int N, Map<Integer, List<int[]>> g) {
        int cost = 0, count = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        boolean[] visited = new boolean[N + 1];
        
        pq.offer(new int[]{1, 0});
        while (!pq.isEmpty()) {
            int[] v = pq.poll();
            //因为是pq，所以出来先出来的是距离最短的 后面如果出来有路径更长的 但是之前已经加过了 就不算了
            //[[1,2,5],[1,3,6],[2,3,1]] 第一次 顶点1进pq, 然后把2，3都加进pq, 当2出队列的时候又把3加进pq，但是这个距离比1,3之间的邻边短，所以下次出对列是2,3
            //当1，3邻边6出队列的时候 3顶点已经算过了，所以就不算了
            if (visited[v[0]]) continue;
            visited[v[0]] = true;
            count += 1;
            cost += v[1];
            
            if (g.containsKey(v[0])) {
                for (int[] nei : g.get(v[0])) {
                    if (!visited[nei[0]]) {
                        pq.offer(new int[]{nei[0], nei[1]});
                    }
                }
            }
        }
        
        return count == N ? cost : -1;
    }
}