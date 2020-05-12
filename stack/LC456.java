class LC456 {
    /**
     * 先统计一下前i个元素的最小值
     * 然后从数组最后面开始做递减栈，每当遇到一个比栈顶元素大的元素就试一下是否满足 左面最小值<栈顶元素<当前元素，但因为是递减栈，所以栈顶元素是当前元素右边最小的
     * 所以如果不满足，就把该元素pop掉，再尝试更靠近尾部的较大的元素，如果如果以当前元素作为最大元素的话，后面没有能够满足条件第二大的元素了，所以就只能让当前元素尝试作为
     * 第二大元素，放在栈顶，继续往左边找更大的
     */
    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        
        int[] min = new int[n];
        min[0] = nums[0];
        
        for (int i = 1; i < n; i++) {
            min[i] = Math.min(nums[i], min[i-1]);
        }
        
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                int k = stack.isEmpty() ? -1 : stack.peek();
                
                if (k != -1 && nums[k] > min[i]) {
                    return true;
                } else {
                    stack.pop();
                }
            }
            
            stack.push(i);
        }
        
        return false;
    }
}