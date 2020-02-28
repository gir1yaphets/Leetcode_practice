class LC81 {
    /**
     * 最坏时间复杂度是所有元素相等，然后找一个不存在的值 e.g[1,1,1,1,1,1] target = 2
     * O(n)
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        
        while (l <= r) {
            int mid = l + (r - l) / 2;
            
            if (nums[mid] == target) {
                return true;
            }
            
            boolean leftSorted = false;
            if (nums[l] < nums[mid]) {
                leftSorted = true;
            } else if (nums[l] == nums[mid]) { //当左边界和中间点相等的时候，可能是从左边界到中间点所有元素都相等，那么这样左半部分是有序的，只要有一个值不想等就认为左边无序
                int p = mid - 1;
                
                while (p >= l) {
                    if (nums[mid] != nums[p]) {
                        break;
                    }
                    
                    p--;
                }
                
                if (p == l - 1) {
                    leftSorted = true;
                }
            }
            
            
            if (leftSorted) {
                if (nums[mid] > target && nums[l] <= target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && nums[r] >= target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        
        return false;
    }

    public boolean search_2(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        
        while (l <= r) {
            int mid = l + (r - l) / 2;
            
            if (nums[mid] == target) {
                return true;
            }
            
            //left is sorted
            if (nums[l] < nums[mid]) {
                if (nums[mid] > target && nums[l] <= target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else if (nums[l] > nums[mid]) { //right is sorted
                if (nums[mid] < target && nums[r] >= target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else {
                l++;//skip one duplicated element
            }
        }
        
        return false;
    }
}