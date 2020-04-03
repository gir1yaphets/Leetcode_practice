/**
 * case 1.当arr的和为负数的时候，那么最大和出现在arr的中间，这时候重复k次的最大值=1个arr的最大值
 * case 2.当arr的和为负数的时候，如果最大值出现在第一组的结尾和第二组的开头，这时候重复k次的最大值=重复两次的最大值e.g [1, -5, 1, 1] [1, -5, 1, 1] max = 结尾的两个1加上开头的1个1
 * case 3.当arr的和为正数的时候，结果为case2 + (k-2) * sum(arr)
 */
public class LC1191 {
    public int kConcatenationMaxSum(int[] arr, int k) {
        int mod = 1000000007;
        
        if (k < 3) return (int) (maxSum(arr, k) % mod);
        
        long sum = 0;
        for (int n : arr) sum += n;
        
        long ans1 = maxSum(arr, 1);
        long ans2 = maxSum(arr, 2);
        
        return (int) ((Math.max(Math.max(ans1, ans2), ans2 + (k-2) * sum)) % mod);
    }
    
    private long maxSum(int[] arr, int repeat) {
        long sum = 0;
        long ans = 0;
        
        for (int i = 0; i < repeat; i++) {
            for (int n : arr) {
                sum = Math.max(0, sum + n);
                ans = Math.max(ans, sum);
            }
        }
        
        return ans;
    }
}