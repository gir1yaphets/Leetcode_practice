class Solution {
    public int findCircleNum_dfs(int[][] M) {
        int n = M.length, count = 0;
        boolean[] visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(M, i, visited);
                count++;
            }
        }
        
        return count;
    }
    
    private void dfs(int[][] M, int start, boolean[] visited) {
        for (int i = 0; i < M.length; i++) {
            if (M[start][i] == 1 && start != i && !visited[i]) {
                visited[i] = true;
                dfs(M, i, visited);
            }
        }
    }

    public int findCircleNum_uf(int[][] M) {
        int n = M.length, count = n;

        int[] root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //每次遇到0的时候都不会merge，所以就把想通的1merge到一起
                if (M[i][j] == 1 && i != j) {
                    int rootx = find(i, root);
                    int rooty = find(j, root);

                    if (rootx != rooty) {
                        root[rooty] = rootx;
                        count--;
                    }
                }
            }
        }
        
        return count;
    }
    
    private int find(int i, int[] root) {
        if (root[i] != i) {
            root[i] = find(root[i], root);
        }
        
        return root[i];
    }
    
}