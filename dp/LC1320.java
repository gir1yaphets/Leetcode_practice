import java.util.*;

class LC1320 {
    public int minimumDistance(String word) {
        int n = word.length();
        //记录在某个位置i的时候左手所在位置和右手所在位置需要的最小cost
        int[][][] memo = new int[n][27][27];
        return dfs(word, 0, 26, 26, memo);
    }
    
    private int dfs(String word, int index, int l, int r, int[][][] memo) {
        if (index == word.length()) return 0;
        
        if (memo[index][l][r] != 0) return memo[index][l][r];
        
        int c = word.charAt(index) - 'A';
        
        int lc = cost(l, c) + dfs(word, index + 1, c, r, memo);
        int rc = cost(r, c) + dfs(word, index + 1, l, c, memo);
        
        return memo[index][l][r] = Math.min(lc, rc);
    }
    
    private int cost(int a, int b) {
        if (a == 26 || b == 26) return 0;
        
        int ax = a / 6, ay = a % 6;
        int bx = b / 6, by = b % 6;
        
        return Math.abs(ax - bx) + Math.abs(ay - by);
    }

    /**
     * 从第一个解法可以优化掉一维，不需要同时记录两根手指的位置，只需要记录前两次手指的位置
     * e.g CAKE
     * 当index在K的时候last finger记录的是C，而prev始终是index-1的位置，记录的是A
     * 如果K用同一根手指打的话就是从prev->curr 这个时候lastfinger还是C
     * 如果换一个手指打的话就是当前的prev变成了lastfinger，lastfinger转移到了curr的位置
     */
    public int minimumDistance_2d(String word) {
        int n = word.length();
        int[][] memo = new int[n][27];
        return dfs(word, 0, 26, memo);
    }
    
    private int dfs(String word, int index, int lastfinger, int[][] memo) {
        if (index == word.length()) return 0;
        
        if (memo[index][lastfinger] != 0) return memo[index][lastfinger];
        
        int prev = index == 0 ? 26 : word.charAt(index - 1) - 'A';
        int c = word.charAt(index) - 'A';
        
        int samefinger = cost(prev, c) + dfs(word, index + 1, lastfinger, memo);
        int anotherfinger = cost(lastfinger, c) + dfs(word, index + 1, prev, memo);
        
        return memo[index][lastfinger] = Math.min(samefinger, anotherfinger);
    }

    /**
     * dp[i][j]表示当前在word的第i个位置并且最前面的一根手指停留在j产生的最小值
     */
    public int minimumDistance_dp(String word) {
        int n = word.length();
        int[][] dp = new int[n + 1][27];
        
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE / 2);
        dp[0][26] = 0;
        int min = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            int prev = i > 0 ? word.charAt(i - 1) - 'A' : 26;
            int curr = word.charAt(i) - 'A';
            
            for (int j = 0; j <= 26; j++) {
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + cost(prev, curr));
                dp[i+1][prev] = Math.min(dp[i+1][prev], dp[i][j] + cost(j, curr));
            }
        }
        
        for (int i = 0; i <= 26; i++) {
            min = Math.min(dp[n][i], min);
        }        
        
        return min;
    }
}