import java.util.*;

class LC210 {
    public int[] findOrder_topo(int n, int[][] pre) {
        if (pre.length == 0) {
            int[] course = new int[n];
            for (int i = 0; i < n; i++) {
                course[i] = i;
            }
            
            return course;
        }
        
        int[] indegree = new int[n];
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < pre.length; i++) {
            int v = pre[i][0];
            indegree[v]++;
        }

        int index = 0;
        int[] res = new int[n];      
        
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }
        
        while (!q.isEmpty()) {
            int v = q.poll();
            res[index++] = v;
            
            for (int i = 0; i < pre.length; i++) {
                if (v == pre[i][1]) {
                    if (--indegree[pre[i][0]] == 0) {
                        q.offer(pre[i][0]);
                    }
                }
            }
        }
        
        //注意判断index和n-1大小 是否有环
        return index < n - 1 ? new int[0] : res;
    }

    /**
     * dfs
     * visited: 0(未访问)，1(访问过)，2(邻接点都访问完)
     */
    private List<Integer>[] table;
    private boolean hasCycle = false;
    private List<Integer> nodeList = new ArrayList<>();
    
    public int[] findOrder_dfs(int n, int[][] pre) {
        int[] visited = new int[n];
        table = new ArrayList[n];
        
        for (int[] edge : pre) {
            int v1 = edge[0], v2 = edge[1];
            if (table[v2] == null) {
                table[v2] = new ArrayList<>();
            }
            
            table[v2].add(v1);
        }
        
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0 && !hasCycle) {
                //dfs
                dfs(i, visited);
            }
        }
        
        if (hasCycle) {
            return new int[0];
        }
        
        int[] res = new int[n];
        
        for (int i = 0; i < nodeList.size(); i++) {
            res[i] = nodeList.get(nodeList.size() - 1 - i);
        }
        
        return res;
    }
    
    private void dfs(int v, int[] visited) {
        if (hasCycle) {
            return;
        }
        
        visited[v] = 1;
        
        //用邻接表的时候一定注意改点的邻接list是不是null
        if (table[v] != null) {
            for (int nb : table[v]) {
                if (visited[nb] == 0) {
                    dfs(nb, visited);
                } else if (visited[nb] == 1) {//因为是有向图 所以只要存在之前访问过的点就是环，即使是当前点v比如[0,1],[1,0]
                    hasCycle = true;
                }
            }
        }
        
        //当所有相邻点都访问过以后把当前点标记为2，并将当前点加入到list中，这样优先加入队列的一定是叶子节点
        visited[v] = 2;
        nodeList.add(v);
    }
}
