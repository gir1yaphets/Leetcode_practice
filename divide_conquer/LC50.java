class Solution {
    public double myPow(double x, int n) {
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        
        return helper(x, n);
    }
    
    /**
     * e.g x^4 = x^2 * x^2: 只需要算x^2就行 不需要算x^3，所以是减少了一半的时间
     * @param x
     * @param n
     * @return
     */
    private double helper(double x, int n) {
        if (n == 0) {
            return 1.0;
        }
        
        double half = helper(x, n / 2);
        
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}