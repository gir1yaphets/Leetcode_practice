package graph;

class LC839 {
    public int numSimilarGroups(String[] A) {
        if (A == null || A.length == 0) return 0;
        int n = A.length;
        
        UF uf = new UF(n);
        
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(A[i], A[j])) {
                    uf.union(i, j);
                }
            }
        }
        
        return uf.n;
    }
    
    class UF {
        int[] root;
        int[] rank;
        int n;
        
        public UF(int n) {
            this.n = n;
            
            rank = new int[n];
            root = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i;
            }
        }
        
        private int find(int x) {
            if (x != root[x]) {
                root[x] = find(root[x]);
            }

            return root[x];
        }

        private void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);

            if (rootx == rooty) {
                return;
            }

            if (rank[rootx] < rank[rooty]) {
                root[rootx] = rooty;
            } else {
                root[rooty] = rootx;
            }
            
            if (rank[rootx] == rank[rooty]) {
                rank[rootx] += 1;
            }
            
            n -= 1;
        }
    }
    
    
    private boolean isSimilar(String a, String b) {
        if (a.equals(b)) return true;
        
        int n = a.length();
        
        char[] ar = a.toCharArray(), br = b.toCharArray();
        int diff = 0;
        
        for (int i = 0; i < n; i++) {
            if (ar[i] != br[i]) {
                if (++diff > 2) return false;
            }
        }
        
        return true;
    }
}