class LC907 {
    public int sumSubarrayMins(int[] A) {
        if (A == null || A.length == 0) return 0;
        
        int n = A.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        
        long sum = 0;
        int mod = 1000000007;
        
        /**
         * 使用单调递增栈，可以找到右边第一个比自己小的新元素，同时也知道左边比自己小的元素，这样就知道了以栈顶元素作为subarray中最小元素的subarray的个数
         */
        for (int i = 0; i < A.length; i++) {
            while (stack.peek() != -1 && A[stack.peek()] > A[i]) {
                int curr = stack.pop();
                int left = stack.peek();

                /**
                 * 这里的长度是i-curr而不是i-curr+1
                 * index:0 1 2 3 4
                 * Array:2 5 5 3 1
                 * 当栈顶元素3遇到1的时候1比3小是不能取的，所以不能+1
                 * 再一点是包含某个index的subarray个数是index左边元素个数(包含index元素本身) * index右边元素个数(也包含index本身)
                 * 即左边元素作为起点的方法*右边元素作为重点的方法
                 */
                sum = (sum + (i - curr) * (curr - left) * A[curr]) % mod;
            }
            
            stack.push(i);
        }
        
        while (stack.peek() != -1) {
            int right = n;
            int index = stack.pop();
            int left = stack.peek();
            
            sum = (sum + (right - index) * (index - left) * A[index]) % mod;
        }
        
        return (int) sum;
    }
}