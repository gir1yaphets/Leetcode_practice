/**
 * Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
    Output: true

    Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
Output: false

Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
Output: false

Input: n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1]
Output: false
 */

class LC_Contest_5710 {
    private Map<Integer, List<Integer>> g = new HashMap<>();
    private boolean hasCycle = false;
    
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        if (n <= 0 || leftChild == null || rightChild == null) {
            return false;
        }
        
        buildGraph(n, leftChild, rightChild);
        boolean[] visited = new boolean[n];
        int count = 0;
        
        for (int i = 0; i < n; i++) {
            if (hasCycle) {
                return false;
            }
            
            if (!visited[i]) {
                count += 1;
                if (count > 1) {
                    return false;
                }
                
                dfs(i, visited);
            }
        }
        
        return true;
    }
    
    private void dfs(int start, boolean[] visited) {
        if (hasCycle) return;
        visited[start] = true;
        
        for (int child : g.get(start)) {
            if (visited[child]) {
                hasCycle = true;
            }
            
            dfs(child, visited);    
        }
    }
    
    private void buildGraph(int n, int[] lc, int[] rc) {
        for (int i = 0; i < n; i++) {
            g.put(i, new ArrayList<>());
        }

        for (int i = 0; i < lc.length; i++) {
            if (lc[i] != -1) {
                g.get(i).add(lc[i]);
            }
            
            if (rc[i] != -1) {
                g.get(i).add(rc[i]);
            }
        }
    }
}