class LC84 {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        
        //为了方便计算
        stack.push(-1);
        for (int i = 0; i < n; i++) {
            //做一个单调递增栈，遇到一个新的较小的值说明之前的某些较大值找到了左边和右边的边界，对其计算面积
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                int h = heights[stack.pop()];
                int w = i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }
            
            stack.push(i);
        }
        
        while (stack.peek() != -1) {
            max = Math.max(max, heights[stack.pop()] * (n - stack.peek() - 1));
        }
        
        return max;
    }
}