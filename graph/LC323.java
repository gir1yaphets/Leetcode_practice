class Solution {
    public int countComponents(int n, int[][] edges) {
        int count = n;
        int[] root = new int[n];
        
        for (int i = 0; i < n; i++) root[i] = i;
        
        for (int i = 0; i < edges.length; i++) {
            int x = edges[i][0], y = edges[i][1];
            
            int rootx = find(x, root);
            int rooty = find(y, root);
            
            if (rootx != rooty) {
                root[rooty] = root[x];
                count--;
            }
        }
        
        return count;
    }
    
    private int find(int i, int[] root) {
        if (i != root[i]) {
            root[i] = find(root[i], root);
        }
        
        return root[i];
    }