import java.util.*;
class LC42 {
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        trap(height);
    }
    public static int trap(int[] height) {
        int len = height.length;
        int maxl = height[0], maxr = height[len - 1];

        int l = 0, r = len - 1;
        int res = 0;
        while (l < r) {
            //每次移动高度较低的指针，因为较低的指针会影响水的高度，比如left = 1, right = 5, 即使right-- = 7也没有用，因为左边较低的还是1
            if (height[l] < height[r]) {
                res += Math.min(maxl, maxr) - height[l];
                //需要更新下一个的值为最大值，比如当前maxl=2,但是下一个值等于3，如果不提前更新则会用2作为下次左边的最大值计算
                maxl = Math.max(maxl, height[++l]);
            } else {
                res += Math.min(maxl, maxr) - height[r];
                maxr = Math.max(maxr, height[--r]);    
            }
        }

        return res;
    }

    public int trap_monostack(int[] height) {
        if (height == null || height.length == 0) return 0;
        
        int n = height.length;
        Stack<Integer> stack = new Stack<>();
        
        int res = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[stack.peek()] < height[i]) {
                int pre = stack.pop();
                if (!stack.isEmpty()) {
                    int left = stack.peek();
                    res += (Math.min(height[left], height[i]) - height[pre]) * (i - left - 1);
                }
            }
            
            stack.push(i);
        }
        
        return res;
    }
}