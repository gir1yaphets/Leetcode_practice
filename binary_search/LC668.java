class LC668 {
    public int findKthNumber(int m, int n, int k) {
        int l = 1, r = m * n;
        
        while (l < r) {
            int mid = l + (r - l) / 2;
            int count = 0;
            for (int i = 1; i <= m; i++) {
                count += Math.min(mid / i, n);
            }
            
            if (count >= k) {
                r = mid;
            } else {
                l = mid + 1;
            } 
        }
        
        return l;
    }
}