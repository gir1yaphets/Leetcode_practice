class LC34_3 {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        
        int lo = findFirstEqual(nums, target);
        int hi = findLastEqual(nums, target);
        
        if (hi == -1) return new int[]{lo, lo};
        
        return new int[]{lo, hi};
    }
    
    //find last equal
    private int findLastEqual(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            
            if (nums[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (nums[end] == target) return end;
        if (nums[start] == target) return start;
        
        return -1;
    }
    
    //find first equal
    private int findFirstEqual(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        
        return -1;
    }
}