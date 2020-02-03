class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums.length == 0) return res;
        
        int l = 0, r = nums.length - 1;
        
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
        
            if (nums[mid] > target) {
                r = mid - 1;
            } else if(nums[mid] < target) {
                l = mid + 1;
            } else {
                int start = mid - 1, end = mid + 1;
                
                while (start >= 0 && nums[mid] == nums[start]) {
                    start--;
                }
                    
                while (end < nums.length && nums[mid] == nums[end]) {
                    end++;
                }
                
                res[0] = start + 1;
                res[1] = end - 1;
                return res;
            }
        }
        
        return res;
    }
}    

