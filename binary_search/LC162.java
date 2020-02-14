class LC162 {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;//-1保证m+1不会越界
        
        while (l < r) {
            int m = l + (r - l) / 2;
            
            if (nums[m] > nums[m + 1]) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        
        return l;
    }
}