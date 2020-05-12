import java.util.*;
public class LC656 {
    public List<Integer> cheapestJump(int[] A, int B) {
        int n = A.length;
        int[] memo = new int[n], next = new int[n];
        
        Arrays.fill(memo, -1);
        Arrays.fill(next, -1);

        List<Integer> path = new ArrayList<>();
        path.add(1);
        
        dfs(A, B, 0, next, memo);
        
        for (int i = 0; i < A.length && next[i] >= 0; i = next[i]) {
            path.add(next[i] + 1);
        }
        
        //判断这条路径是否走到了最后
        if (path.contains(A.length)) {
            return path;
        }
        
        return new ArrayList<>();
    }
    
    /**
     * next[i]记录当前从当前index开始 下一步跳到哪个index能保证cost最小
     */
    private int dfs(int[] A, int B, int index, int[] next, int[] memo) {
        //这里为什么是index == A.length - 1不太清楚 
        //如果是index == A.length A = [0,0,0,0,0,0] B = 3这个case过不了
        if (index == A.length - 1) return 0; 
        
        if (memo[index] != -1) return memo[index];
        
        int minCost = Integer.MAX_VALUE;
        int minIndex = -1;
        
        for (int i = 1; i <= B; i++) {
            if (index + i < A.length && A[index + i] != -1) {
                int cost = dfs(A, B, index + i, next, memo);
                if (minCost > cost) {
                    minCost = cost;
                    minIndex = index + i;
                }
            }            
        }
        
        next[index] = minIndex;
        memo[index] = A[index] + minCost;
        
        return memo[index];
    }

    public List<Integer> cheapestJump_dp(int[] A, int B) {
        int n = A.length;
        int[] dp = new int[n];
        int[] path = new int[n];
        int[] len = new int[n]; //记录到i的时候 最短距离的数组长度 数组长度越长说明排序越小
        
        Arrays.fill(dp, Integer.MAX_VALUE);
        Arrays.fill(path, -1);
        
        dp[0] = 0;
        
        for (int i = 0; i < n; i++) {
            if (A[i] == -1) continue;
            for (int j = Math.max(0, i-B); j < i; j++) {
                if (A[j] == -1) continue;
                
                int cost = A[i] + dp[j];
                if (cost < dp[i] || cost == dp[i] && len[i] < len[j] + 1) {
                    dp[i] = cost;
                    path[i] = j;
                    len[i] = len[j] + 1;
                }
            }
        }
        
        List<Integer> res = new ArrayList<>();
        
        for (int cur = n - 1; cur >= 0; cur = path[cur]) res.add(0, cur + 1);
        
        return res.get(0) != 1 ? Collections.emptyList() : res;
    }
}