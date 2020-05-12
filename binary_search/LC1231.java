class LC1231 {
    public int maximizeSweetness(int[] sweetness, int K) {
        int n = sweetness.length;
        long lower = 1, upper = 0;
        long sum = 0;
        
        for (int i = 0; i < n; i++) {
            sum += sweetness[i];
        }
        
        upper = sum / (K + 1);
        
        //lower和upper是以某个值作为每一份的最小值进行切蛋糕，判断以该值为每一份的最小值是否能切成K+1份，如果不能说明最小值选大了
        //如果能切成K+1份，保留该值，并判断是否有更大的值也可以切成K+1份，所以相当于找左边的最后一个满足条件的值，这个时候解空间在左边
        //要移动右指针，舍弃掉右边不满足条件的值
        long l = lower, r = upper;
        while (l < r) {
            //找最后一个值的时候m要取中点+1,相当于取的是right的值，比如l=6,r=7,如果(l+r)/2是等于left的，而(l+r)/2+1是等于right的
            //如果这时候取left，那么left还保持不动，就会出现死循环
            long m = l + (r - l) / 2 + 1;
            
            if (func(sweetness, m) < K + 1) {
                r = m - 1;
            } else {
                l = m;
            }
        }
        
        return (int) l;
    }
    
    private int func(int[] a, long m) {
        int sum = 0, k = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            if (sum >= m) {
                sum = 0;
                k += 1;
            }
        }
        
        return k;
    }
}