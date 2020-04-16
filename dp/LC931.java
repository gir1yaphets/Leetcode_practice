
import java.util.*;

public class LC931 {
    public static void main(String[] args) {
        int[][] A = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        new LC931().minFallingPathSum(A);
    }

    public int minFallingPathSum(int[][] A) {
        if (A == null || A.length == 0) return 0;
        
        int n = A.length;
        int[][] dp = new int[n][n];
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            dp[0][i] = A[0][i];
            min = Math.min(min, dp[0][i]);
        }
        
        for (int i = 1; i < n; i++) {
            min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                int num = dp[i-1][j];
                if (j > 0) num = Math.min(num, dp[i-1][j-1]);
                if (j < n - 1) num = Math.min(num, dp[i-1][j+1]);
                
                dp[i][j] = num + A[i][j];
                min = Math.min(min, dp[i][j]);
            }
        }
        
        return min;
    }

    public int minFallingPathSum_1d(int[][] A) {
        if (A == null || A.length == 0) return 0;
        
        int n = A.length;
        int[] dp = new int[n];
        
        int min = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            dp[i] = A[0][i];
            min = Math.min(min, dp[i]);
        }
        
        for (int i = 1; i < n; i++) {
            int[] next = new int[n];
            Arrays.fill(next, Integer.MAX_VALUE);
            min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                next[j] = dp[j];
                if (j > 0) next[j] = Math.min(next[j], dp[j-1]);
                if (j < n - 1) next[j] = Math.min(next[j], dp[j+1]);
                
                next[j] += A[i][j];
                min = Math.min(min, next[j]);
            }
            
            dp = next;
        }
        
        return min;
    }
}