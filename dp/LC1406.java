import java.util.*;

public class LC1406 {
    public String stoneGameIII(int[] stoneValue) {
        int[] memo = new int[stoneValue.length];
        Arrays.fill(memo, Integer.MIN_VALUE);
        int score = dfs(stoneValue, 0, memo);
        
        return score > 0 ? "Alice" : score == 0 ? "Tie" : "Bob";
    }
    
    /**
     * 这种两人下棋的问题不需要考虑当前是谁在走，只需要看当前走这一步能获得relative score是多少即可
     */
    private int dfs(int[] sv, int index, int[] memo) {
        if (index == sv.length) return 0;
        
        if (memo[index] != Integer.MIN_VALUE) return memo[index];
        
        int sum = 0;
        for (int i = 0; i <= 2 && index + i < sv.length; i++) {
            sum += sv[index + i];
            memo[index] = Math.max(memo[index], sum - dfs(sv, index + i + 1, memo));
        }
        
        return memo[index];
    }

    public String stoneGameIII_DP(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1]; //需要多出来一个，当有人取最后一个的时候另一个人能取的就是0

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Integer.MIN_VALUE;
            
            for (int k = 0, sum = 0; k <= 2 && i + k < n; k++) {
                sum += stoneValue[i + k];
                dp[i] = Math.max(dp[i], sum - dp[i + k + 1]);
            }
        }
        
        return dp[0] > 0 ? "Alice" : dp[0] == 0 ? "Tie" : "Bob";
    }
}