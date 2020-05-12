import java.util.*;

class LC514 {
    private Map<Character, List<Integer>> map = new HashMap<>();
    private int min = Integer.MAX_VALUE;
    
    public int findRotateSteps_TLE(String ring, String key) {
        char[] chr = ring.toCharArray(), chk = key.toCharArray();
        
        for (int i = 0; i < chr.length; i++) {
            char c = chr[i];
            map.putIfAbsent(c, new ArrayList<>());
            map.get(c).add(i);
        }
        
        dfs(chr, chk, 0, 0, 0);
        return min;
    }
    
    private void dfs(char[] chr, char[] chk, int lastpos, int index, int step) {
        if (step > min) return;
        
        if (index == chk.length) {
            min = Math.min(step, min);
            return;
        }
        
        for (int pos : map.get(chk[index])) {
            dfs(chr, chk, pos, index + 1, step + Math.abs(pos - lastpos) + 1);
            
            dfs(chr, chk, pos, index + 1, step + chr.length - Math.abs(pos - lastpos) + 1);
        }
    }
    
    public int findRotateSteps_memo(String ring, String key) {
        char[] chr = ring.toCharArray(), chk = key.toCharArray();
        
        for (int i = 0; i < chr.length; i++) {
            char c = chr[i];
            map.putIfAbsent(c, new ArrayList<>());
            map.get(c).add(i);
        }
        
        int n = chr.length;
        int[][] memo = new int[chk.length][n];
        
        return dfs(chr, chk, 0, 0, memo) + chk.length;
    }
    
    private int dfs(char[] chr, char[] chk, int lastpos, int index, int[][] memo) {
        if (index == chk.length) {
            return 0;
        }
        
        if (memo[index][lastpos] != 0) return memo[index][lastpos];
        
        int min = Integer.MAX_VALUE;
        for (int pos : map.get(chk[index])) {
            int dist = Math.min(Math.abs(pos - lastpos), chr.length - Math.abs(pos - lastpos));
            //注意这里需要取所有中的最短的的
            min = Math.min(min, dist + dfs(chr, chk, pos, index + 1, memo));
        }
        
        memo[index][lastpos] = min;
        return min;
    }

    public int findRotateSteps_top_down(String ring, String key) {
        char[] chr = ring.toCharArray(), chk = key.toCharArray();
        
        for (int i = 0; i < chr.length; i++) {
            char c = chr[i];
            map.putIfAbsent(c, new ArrayList<>());
            map.get(c).add(i);
        }
        
        int m = chr.length, n = chk.length;
        int[][] dp = new int[n][m];
        
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        
        for (int pos : map.get(chk[0])) {
            dp[0][pos] = Math.min(dp[0][pos], Math.min(pos, m - pos));
        }
        
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            //当前这一位可能存在多个相等的字母比如godding gd, 如果当前想停在d可能是index = 2, 也可能是index = 3
            min = Integer.MAX_VALUE;
            for (int pos : map.get(chk[i])) {
                //前一位也有多个index的可能
                for (int pre : map.get(chk[i - 1])) {
                    int dist = Math.min(Math.abs(pos - pre), m - Math.abs(pos - pre));
                    dp[i][pos] = Math.min(dp[i][pos], dist + dp[i-1][pre]);
                }
                min = Math.min(min, dp[i][pos]);
            }
        }
        
        return min + n;
    }
}