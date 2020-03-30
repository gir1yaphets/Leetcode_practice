package backtrack;
/**
 * 1.不需要关心()的运算，括号只是指定先运算哪两个数
 * 2.每次先指定两个数进行运算，然后运算出一个结果和其它数字再进行运算
 */
class LC679 {
    public boolean judgePoint24(int[] nums) {
        int n = nums.length;
        if (n <= 0) return false;
        
        double[] a = new double[n];
        
        for (int i = 0; i < n; i++) {
            a[i] = nums[i];
        }
        
        return dfs(a);
    }
    
    private boolean dfs(double[] a) {
        //注意绝对值
        if (a.length == 1) return Math.abs(a[0] - 24) < 0.001;
        
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                double[] b = new double[a.length - 1];
                
                int index = 0;
                for (int k = 0; k < a.length; k++) {
                    //将本次没有被选中的数字拷贝到b数组中，留着继续dfs
                    if (k == i || k == j) continue;
                    
                    b[index++] = a[k];
                }
                
                //遍历将本次进行运算的结果和剩余的数字进行dfs
                for (double d : compute(a[i], a[j])) {
                    b[index] = d;
                    
                    if (dfs(b)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private double[] compute(double x, double y) {
        return new double[]{x + y, x - y, y - x, x * y, x / y, y / x};
    }
}