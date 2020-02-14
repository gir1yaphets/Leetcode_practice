class LC69 {
    /**
     * 这道题是找上界，所以可以找到 >根号值的下界，然后再减去1
     */
    public int mySqrt(int x) {
        int lo = 1, hi = x;

        if (x < 2) return x;
        
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            
            if (mid > x / mid) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        
        return lo - 1;
    }
}