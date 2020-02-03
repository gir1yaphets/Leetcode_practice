class Solution {
    public boolean validTree_uf(int n, int[][] edges) {
        int count = n;
        int[] root = new int[n];
        for (int i = 0; i < n; i++) root[i] = i;
        
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0], y = edges[i][1];
            
            int rootx = find(x, root);
            int rooty = find(y, root);
            
            if (rootx != rooty) {
                root[rooty] = rootx;
                count--;
            } else {
                //注意这里：因为一开始都是root[i] = i的，所以如果发现两个node的root是一样的，则说明有环
                return false;
            }
        }
        
        return count == 1;
    }
    
    private int find(int i, int[] root) {
        if (i != root[i]) {
            root[i] = find(root[i], root);
        }
        
        return root[i];
    }

    /**
     * 
     * @param n
     * @param edges
     * @return
     */
    public boolean validTree_dfs(int n, int[][] edges) {
        if (edges.length == 0) return n == 1;
        
        int count = 0;
        
        List<Integer>[] table = new ArrayList[n];
        
        for (int i = 0; i < edges.length; i++) {
            int v1 = edges[i][0], v2 = edges[i][1];
            
            if (table[v1] == null) {
                table[v1] = new ArrayList<>();
            }
            
            if (table[v2] == null) {
                table[v2] = new ArrayList<>();
            }
            
            table[v1].add(v2);
            table[v2].add(v1);
        }
        
        boolean[] vis = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                if (count > 1 || cycle(table, -1, i, vis)) {
                    return false;
                }
                
                count++;
            }
        }
        
        return true;
    }
    
    /**
     * 判断graph是否存在环: 用visited数组和前节点，而且要在dfs之前判断是否存在一个邻点v visited[v] && v != pre
     * 就是如果该邻节点已经访问过，但还不是从之前过来的节点，那么一定是环
     * @param table
     * @param pre
     * @param start
     * @param vis
     * @return
     */
    private boolean cycle(List<Integer>[] table, int pre, int start, boolean[] vis) {
        
        vis[start] = true;

        if (table[start] != null) {
            for (int v : table[start]) {
                if (vis[v] && v != pre) {
                    return true;
                }

                if (!vis[v] && cycle(table, start, v, vis)) {
                    return true;
                }
            }
        }
        
        return false;
    }
}