class LC875 {
    public int minEatingSpeed(int[] piles, int H) {
        int n = piles.length;
        long lower = 1, upper = 0;
        
        for (int i = 0; i < n; i++) upper += piles[i];
        
        long l = lower, r = upper;
        
        while (l < r) {
            long m = l + (r - l) / 2;
            
            long h = 0;
            for (int i = 0; i < n; i++) {
                h += piles[i] % m == 0 ? piles[i] / m : piles[i] / m + 1;
            }
            
            if (h > H) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        
        return (int) l;
    }
}