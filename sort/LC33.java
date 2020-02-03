class LC33 {
    public static void main(String[] args) {
        int[] nums = {4,5,1,2,3};
        new LC33().find_rotate_index(nums, 0, nums.length - 1);
    }

    public int find_rotate_index(int[] nums, int left, int right) {
        if (nums[left] < nums[right])
            return 0;

        while (left <= right) {
            int pivot = (left + right) / 2;
            //如果当前中点正好是分割点 直接返回
            if (nums[pivot] > nums[pivot + 1])
                return pivot;
            else {
                //如果中点的值小于左边的值，说明发生转动的分割点在左半部
                if (nums[pivot] < nums[left])
                    right = pivot - 1;
                    //如果中点的值小于右边的值，说明发生转动的分割点在右半部
                else
                    left = pivot + 1;
            }
        }
        return 0;
    }

    public int search(int[] nums, int target) {
        if (nums.length == 0)
            return -1;

        int index = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                index = i;
            }
        }

        if (target >= nums[0] && target <= nums[index]) {
            return binarySearch(nums, 0, index, target);
        }

        return binarySearch(nums, index + 1, nums.length - 1, target);

    }

    private int binarySearch(int[] nums, int start, int end, int target) {
        //注意递归终止条件
        if (start > end)
            return -1;

        int mid = start + (end - start) / 2;

        int result = -1;

        if (nums[mid] > target) {
            result = binarySearch(nums, start, end - 1, target);
        } else if (nums[mid] < target) {
            result = binarySearch(nums, mid + 1, end, target);
        } else {
            result = mid;
        }

        return result;
    }
}