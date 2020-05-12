import java.util.*;

public class LC265 {
    public int minCostII_NK2(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int m = costs.length, n = costs[0].length;
        //dp[i][j]表示第i间房子使用第j个color能获得的最小值
        int[][] dp = new int[m][n];
        
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            dp[0][i] = costs[0][i];
            min = Math.min(min, dp[0][i]);
        }
        
        for (int i = 1; i < m; i++) {
            min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (j == k) continue;
                    
                    dp[i][j] = Math.min(dp[i][j], costs[i][j] + dp[i-1][k]);
                }
                min = Math.min(min, dp[i][j]);
            }
        }
        
        return min;
    }

    public int minCostII_NK(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int m = costs.length, n = costs[0].length;
        
        //记录之前的最小值和第二小值，如果这次要用的color index和之前最小值的index一样，那么这个当前这个house如果使用这个color只能从之前第二小的值转移过来
        int preMin = 0, preSecMin = 0, minIndex = -1;
        
        for (int i = 0; i < m; i++) {
            int min = Integer.MAX_VALUE, secMin = Integer.MAX_VALUE, index = -1;
            for (int j = 0; j < n; j++) {
                int cost = costs[i][j] + (j == minIndex ? preSecMin : preMin);
                
                if (cost < min) {
                    secMin = min;
                    min = cost;
                    index = j;
                } else if (cost < secMin) {
                    secMin = cost;
                }
            }
            
            minIndex = index;
            preMin = min;
            preSecMin = secMin;
        }
        
        return preMin;
    }
}