class LC33_2 {
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        
        int l = 0, r = nums.length - 1;
        
        while (l <= r) {
            int mid = l + (r - l) / 2;
            
            if (target == nums[mid]) {
                return mid;
            }
            
            //二分搜索只能在有序的数列中进行
            //先判断左边是否有序，如果有序并且target也在这个范围中，那么直接二分到左边区域
            if (nums[l] <= nums[mid]) {
                if (target >= nums[l] && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            
            //如果右边是否有序，如果有序并且target也在这个范围中，那么直接二分到右边区域
            if (nums[mid] <= nums[r]) {
                if (target >= nums[mid] && target <= nums[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        
        return -1;
        
    }
}