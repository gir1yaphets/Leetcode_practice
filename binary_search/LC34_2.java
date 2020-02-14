class LC34_2 {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};
        
        int l = 0, r = nums.length;
        
        //lower bound
        while (l < r) {
            int m = l + (r - l) / 2;
            
            if (nums[m] > target - 1) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        
        int lo = -1, hi = -1;
        //注意这里找的是第一个>target的值可能不存在 所以要判断l < nums.length e.g.[2,2] target = 3
        if (l < nums.length && nums[l] == target) {
            lo = l;
        } else {
            return new int[]{-1, -1};
        }
        
        l = 0;
        r = nums.length;
        
        //higer bound
        while (l < r) {
            int m = l + (r - l) / 2;
            
            if (nums[m] > target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        hi = l - 1;
        
        return new int[]{lo, hi};
    }
}