class LC1283 {
    public int smallestDivisor(int[] nums, int threshold) {
        int n = nums.length;
        int lower = 1, upper = 0;
        for (int i = 0; i < n; i++) upper = Math.max(upper, nums[i]);
        
        int l = lower, r = upper;
        while (l < r) {
            int m = l + (r - l) / 2;
            
            if (isLessOrEqual(nums, m, threshold)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        
        return l;
    }
    
    private boolean isLessOrEqual(int[] nums, int target, int threshold) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i] % target == 0 ? nums[i] / target : nums[i] / target + 1;
            
            sum += a;
            if (sum > threshold) {
                return false;
            }
        }
        
        return true;
    }
}