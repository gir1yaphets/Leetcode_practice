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
                maxl = Math.max(maxl, height[++l]);
            } else {
                res += Math.min(maxl, maxr) - height[r];
                maxr = Math.max(maxr, height[--r]);    
            }
        }

        return res;
    }
}