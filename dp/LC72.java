class LC72 {
    /**
     * dp[i][j]: 长度为i的word1变成长度为j的word2的最短步数
     * @return
     */
    public int minDistance(String word1, String word2) {
        int l1 = word1.length(), l2 = word2.length();
        
        int[][] dp = new int[l1 + 1][l2 + 1];
        
        //当一个字符为空串的时候，从另一个字符变为空串需要字符长度的步数
        for (int i = 0; i <= l1; i++) {
            dp[i][0] = i;
        }
        
        for (int j = 0; j <= l2; j++) {
            dp[0][j] = j;
        }
        
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                //如果word1的最后一位和word2的最后一位相同，直接取掉，比较两个的前一位
                //如果word1的最后一位和word2的最后一位不相同，从dp[i-1][j-1]花一步将最后一位从word1的最后一位改为word2的最后一位
                int cost = (word1.charAt(i-1) == word2.charAt(j-1)) ? 0 : 1;
                
                dp[i][j] = Math.min(cost + dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]) + 1);
            }
        }
        
        return dp[l1][l2];
    }

    public int minDistance_memoDfs(String word1, String word2) {
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        return dfs(word1, word2, word1.length(), word2.length(), memo);
    }
    
    private int dfs(String word1, String word2, int l1, int l2, int[][] memo) {
        if (l1 == 0) return l2;
        if (l2 == 0) return l1;
        
        if (memo[l1][l2] != 0) return memo[l1][l2];
        
        //最后一位相等，直接check 两个单词的len - 1长度
        if (word1.charAt(l1 - 1) == word2.charAt(l2 - 1)) {
            memo[l1][l2] = dfs(word1, word2, l1 - 1, l2 - 1, memo);
        } else {
            //1 + min(delete, insert, replace)
            memo[l1][l2] = 1 + Math.min(Math.min(dfs(word1, word2, l1 - 1, l2, memo), dfs(word1, word2, l1, l2 - 1, memo)), dfs(word1, word2, l1 - 1, l2 - 1, memo));
        }
        
        return memo[l1][l2];
    }
}