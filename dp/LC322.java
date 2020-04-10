import java.util.*;

public class LC322 {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        new LC322().coinChange(coins, 11);
    }

    /**
     * bottom-up solution 从最小的子问题开始向大扩张
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i-coin] + 1);
            }
        }
        
        return dp[amount] >= amount + 1 ? -1 : dp[amount];
    }

    /**
     * dfs
     */
    private int res = Integer.MAX_VALUE;
    
    public int coinChange_dp(int[] coins, int amount) {
        Arrays.sort(coins);
        
        dfs(coins, amount, coins.length - 1, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    
    /**
     * 从面值大的硬币开始尝试
     * 强剪枝，如果当前数量超过了之前的结果，直接不再进行计算
     */
    private void dfs(int[] coins, int left, int index, int curr) {
        if (left == 0) {
            res = Math.min(curr, res);
            return;
        }
        
        int coin = coins[index];
        
        if (index == 0) {
            if (left % coin == 0) {
                res = Math.min(curr + left / coin, res);
            }
        } else {
            int k = left / coin;
            for (int i = k; i >= 0 && curr + i < res; i--) {
                dfs(coins, left - i * coin, index - 1, curr + i);
            }
        }
    }
}