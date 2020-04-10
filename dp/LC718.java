public class LC718 {
    /**
     * 这道题是求最长的subarray，所以当A[i-1] != B[j-1]的时候不能连续计数
     * dp[i][j]:以A[i-1]和B[j-1]结尾的最长subarray的长度
     * 而且最长的subarray不一定出现在最后，所以需要用max统计中间结果
     */
    public int findLength(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int[][] dp = new int[m+1][n+1];
        
        int max = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A[i-1] == B[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    max = Math.max(dp[i][j], max);
                } 
            }
        }
        
        return max;
        
    }
}