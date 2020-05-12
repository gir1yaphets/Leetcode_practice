class LC1140 {
    /**
     * 这是二维dp，只记录一个int[] memo是不对的
     * 需要memo[i][j]表示在di个位置取j个数能获得的最大分数
     */
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int sum = 0;
        int[][] memo = new int[n][n];
        for (int p : piles) sum += p;
        int relative = dfs(piles, 0, 1, memo);
        
        return (relative + sum) / 2;
    }
    
    private int dfs(int[] p, int index, int num, int[][] memo) {
        if (index == p.length) return 0;
        
        if (memo[index][num] != 0) return memo[index][num];
        
        int score = Integer.MIN_VALUE;
        for (int i = 0, sum = 0; i < 2 * num && index + i < p.length; i++) {
            sum += p[index + i];
            score = Math.max(score, sum - dfs(p, index + i + 1, Math.max(num, i + 1), memo));
        }
        
        memo[index][num] = score;
        
        return score;
    }
}