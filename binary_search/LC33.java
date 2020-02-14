class LC33 {
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        
        int l = 0, r = nums.length - 1;
        
        while (l < r) {
            int m = l + (r - l) / 2;
            
            //left part unsorted, right part sorted
            if (nums[m] < nums[l]) {
                if (nums[m] < target && nums[r] >= target) {
                    l = m + 1;
                } else {
                    r = m;
                }
            } else {
                if (nums[m] >= target && nums[l] <= target) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
        }
        
        return nums[l] == target ? l : -1;
    }
}