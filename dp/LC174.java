import java.util.*;

public class LC174 {
    public int calculateMinimumHP(int[][] dun) {
        int m = dun.length, n = dun[0].length;
        int[][] dp = new int[m + 1][n + 1]; //dp[i][j]表示到达(i,j)需要的最小hp值，不需要管正数
        
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        dp[m][n-1] = dp[m-1][n] = 1;
        
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] = Math.max(Math.min(dp[i+1][j], dp[i][j+1]) - dun[i][j], 1); //如果减完以后是负数，说明dun[i][j]是正数，直接更新成1，即有1点hp就能到达这个点
            }
        }
        
        return dp[0][0];
    }
}