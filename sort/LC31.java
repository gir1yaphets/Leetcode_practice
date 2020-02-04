class LC31 {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        
        int n = nums.length;
        int asIndex = n - 2;
        
        //Step1: 从最后一个开始 找到第一个上升序列的位置，如果整个数组都是下降顺序，则只需要将其整体reverse就行了
        //[3,5,4,2,1]找到第一个上升的index asIndex = 1即[3]的位置
        while (asIndex >= 0 && nums[asIndex] >= nums[asIndex + 1]) {
            asIndex--;
        }
        
        //Step2: asIndex >= 0表示存在一个上升的位置，此时将这个元素与后半部分的下降序列的第一个比它大的数字交换，这样保证最终找到的是比当前数字大的最小的那个数字
        //[3,5,4,2,1]最小的比当前元素大的是[4]，将这两个元素交换 变成[4,5,3,2,1]
        if (asIndex >= 0) {
            int firstLargerIndex = n - 1;
            while (firstLargerIndex > 0 && nums[firstLargerIndex] <= nums[asIndex]) {
                firstLargerIndex -= 1;
            }

            swap(nums, asIndex, firstLargerIndex);
        }
        

        //Step3: 将整个有半部分的值按照从小到大排列 保证最终有半部分是最小的一个值
        //[4,1,2,3,5]
        reverseSort(nums, asIndex + 1, n - 1);
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void reverseSort(int nums[], int i, int j) {
        if (i >= j) return;
        
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}