class LC153 {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        int min = Integer.MAX_VALUE;
        
        while (l <= r) {
            int mid = l + (r - l) / 2;
            
            //left sorted: 如果左边是有序的左边的最小值一定出现在nums[l],有可能比左边最小值小的一定出现在右半部分
            if (nums[l] <= nums[mid]) {
                min = Math.min(min, nums[l]);
                l = mid + 1;
            } else {
                min = Math.min(min, nums[mid]);
                r = mid - 1;
            }
        }
        
        return min;
    }
}