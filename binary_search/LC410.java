import java.util.*;
class LC410 {
    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        //对分成的组的和进行二分，找一个值刚好能将nums以最大和为m一组，最终分成k组
        int lower = Integer.MIN_VALUE;
        long upper = 0;
        for (int i = 0; i < n; i++) {
            lower = Math.max(lower, nums[i]);
            upper += nums[i];
        }
        
        if (k == 1) return (int) upper;
        
        long l = lower, r = upper + 1;
        while (l < r) {
            long m = l + (r - l) / 2;
            
            //func是以和小于等于m的数分成一组需要的组数
            if (func(nums, m) > k) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        
        return (int) l;
    }
    
    // 7 2 5 10 8
    // l = 10, r = 32 m = 21 (7 2 5) (10 8) 分成两组, 移动r=m, 继续找更小的m使得func(m) == k
    // l = 10, r = 21 m = 15 (7 2 5) (10) (8) 分成三组了,说明m取小了,舍弃掉这个m, l = m + 1
    // l = 16, r = 21 m = 18 (7 2 5) (10 8) 分成两组, 移动r=m, 继续找更小的m使得func(m) == k
    // l = 16, r = 18 m = 17 (7 2 5) (10) (8) 分成三组了,说明m取小了,舍弃掉这个m, l = m + 1
    // l = 18, r = 18 return l或r
    
    //1.当m满足func(m) <= k的时候，并没有停下来，而是继续移动r看是否存在更小的值使得func(m) <= k
    //2.当找到一个最小的m，刚好使得func(m)是以和为m一组满足最终能分成k组的时候，这个时候r=m就不再动了
    //3.如果此时l仍然不等于r的话，那么会继续寻找m, 但是这个m一定比第二步中确定的m值要小(因为m = (l+r)/2)，如果刚刚2中的m已经是满足能分成k组的最小值的m话，那么当前这个新的m一定不可能满足分成的组还等于k组，
    //  一定是大于k组的，所以此时移动l = m + 1, 只要不满足l==r，就会反复的移动l = m + 1,舍弃掉那些以m为和的组但是最终总数会大于k的m，最后达到l==r, 
    //  而r又是一组数的和使得刚好分成了k组，所以l和r一定在array的和中出现。
    
    private int func(int[] nums, long m) {
        int k = 1, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > m) {
                sum = nums[i];
                k++;
            } 
        }
        
        return k;
    }

    public int splitArray_dp(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[n+1][m+1];
        
        int[] sum = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        
        for (int i = 1; i <= n; i++) {
            dp[i][1] = sum[i];
        }
        
        for (int k = 2; k <= m; k++) {
            for (int i = k; i <= n; i++) {
                for (int j = k-1; j < i; j++) {
                    dp[i][k] = Math.min(dp[i][k], Math.max(dp[j][k-1], sum[i] - sum[j]));
                }
            }
        }
        
        return dp[n][m];
    }
}