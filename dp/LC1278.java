import java.util.*;
class LC1278 {
    public static void main(String[] args) {
        String s = "aabbc";
        new LC1278().palindromePartition(s, 2);
    }

    public int palindromePartition(String s, int K) {
        int n = s.length();
        int[][] dp = new int[n + 1][K + 1];
        int[][] cache = new int[n][n];
        char[] ca = s.toCharArray();
        
        for (int[] row : cache) Arrays.fill(row, -1);

        for (int k = 1; k <= K; k++) {
            for (int i = k; i <= n; i++) {
                //k = 1需要单独算，即i个元素分成1组
                if (k == 1) {
                    dp[i][1] = makePalindrome(ca, 0, i - 1, cache);
                } else {
                    dp[i][k] = Integer.MAX_VALUE;
                    for (int j = k - 1; j < i; j++) {
                        dp[i][k] = Math.min(dp[i][k], dp[j][k-1] + makePalindrome(ca, j, i - 1, cache));
                    }
                }
            }
        }
        
        return dp[n][K];
    }
    
    private int makePalindrome(char[] ca, int start, int end, int[][] cache) {
        if (cache[start][end] != -1) return cache[start][end];
        
        int l = start, r = end;
        int cost = 0;
        while (l < r) {
            if (ca[l++] != ca[r--]) {
                cost += 1;
            }
        }
        
        
        cache[start][end] = cost;
        return cost;
    }
}