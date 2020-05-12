import java.util.*;
public class LC1340 {
    /**
     * T O(n*d) 对于每个点，它的相邻点有2d个
     */
    private int[] memo;
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        memo = new int[n];
        int res = 1;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dfs(arr, d, i));
        }
        
        return res;
    }
    
    private int dfs(int[] arr, int d, int start) {
        if (memo[start] != 0) return memo[start];
        
        int right = 1, left = 1;
        for (int i = 1; i <= d; i++) {
            if (start + i >= arr.length) break;
            
            //check start ~ start + i exists arr[i] >= arr[start]
            if (arr[start + i] >= arr[start]) break;
            
            right = Math.max(right, 1 + dfs(arr, d, start + i));
        }
        
        for (int i = 1; i <= d; i++) {
            if (start - i < 0) break;
            if (arr[start - i] >= arr[start]) break;
            
            left = Math.max(left, 1 + dfs(arr, d, start - i));
        }
        
        return memo[start] = Math.max(left, right);
    }

    public int maxJumps_dp(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n];
        int[][] sorted = new int[n][2];
        
        for (int i = 0; i < n; i++) {
            sorted[i][0] = arr[i];
            sorted[i][1] = i;
        }
        
        //需要先按照高度排序，然后从高度最高的开始计算，这样保证dp[j]依赖于dp[i]的时候，由于arr[i]>arr[j] 所以dp[i]已经先被计算完了
        Arrays.sort(sorted, (o1, o2) -> o2[0] - o1[0]);
        Arrays.fill(dp, 1);
        
        for (int[] item : sorted) {
            int i = item[1];
            for (int j = i + 1; j <= Math.min(i + d, n - 1) && arr[i] > arr[j]; j++) {
                dp[j] = Math.max(dp[j], 1 + dp[i]);
            }
            
            for (int j = i - 1; j >= Math.max(i - d, 0) && arr[i] > arr[j]; j--) {
                dp[j] = Math.max(dp[j], 1 + dp[i]);
            }
        }
        
        int res = 1;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }        
        
        return res;
    }
}