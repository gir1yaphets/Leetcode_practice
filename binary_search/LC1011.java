class LC1011 {
    public int shipWithinDays(int[] weights, int D) {
        int n = weights.length;
        long lower = 1, upper = 0;
        
        for (int i = 0; i < n; i++) {
            lower = Math.max(lower, weights[i]);
            upper += weights[i];
        }
        
        long l = lower, r = upper;
        while (l < r) {
            long m = l + (r - l) / 2;
            
            if (within(weights, m, D)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        
        return (int) l;
    }
    
    private boolean within(int[] weights, long target, int D) {
        int sum = 0, group = 1;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (sum > target) {
                sum = weights[i];
                group += 1;
            }
            
            if (group > D) {
                return false;
            }
        }
        
        return true;
    }
}