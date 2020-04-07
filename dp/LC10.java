class LC10 {
    public boolean isMatch(String s, String p) {
        return dfs(s, p);
    }
    
    private boolean dfs(String s, String p) {
        if (p.length() == 0) return s.length() == 0;
        
        //第一位是否匹配
        boolean firstMatch = s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        
        /**
         * p的前两位中第二位是*
         * case1: *前面的字符使用0次，比较p的*后面的字符和s是否匹配
         * case2: *当第一位字符匹配的情况下，*前面字符要使用多次，比较p和s的剩余字符比较
         */
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return dfs(s, p.substring(2)) || (firstMatch && dfs(s.substring(1), p));
        } else {
            //不包含*的时候则一位一位比较
            return firstMatch && dfs(s.substring(1), p.substring(1));
        }
    }

    /**
     * substring换成index
     * @param s
     * @param p
     * @param indexs
     * @param indexp
     * @return
     */
    private boolean dfs(String s, String p, int indexs, int indexp) {
        if (indexp == p.length()) return indexs == s.length();
        
        boolean firstMatch = indexs < s.length() && (s.charAt(indexs) == p.charAt(indexp) || p.charAt(indexp) == '.');
        
        if (indexp < p.length() - 1 && p.charAt(indexp + 1) == '*') {
            return dfs(s, p, indexs, indexp + 2) || (firstMatch && dfs(s, p, indexs + 1, indexp));
        } else {
            return firstMatch && dfs(s, p, indexs + 1, indexp + 1);
        }
    }

    /**
     * dp[i][j]: s中的以i为长度的字符串和p中以j为长度的字符串是否可以匹配
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch_dp(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        
        //空串: 可以matrch
        dp[0][0] = true;

        //s是空串, 当p中有一位是*的时候看前两位(即去掉*和*前一位) e.g s = ""; p = "a*b*"
        for (int i = 2; i <= n; i++) {
            if (p.charAt(i-1) == '*') {
                dp[0][i] = dp[0][i-2];
            }
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i-1);
                char pc = p.charAt(j-1);
                
                //当前字符相等，匹配结果=[i-1][j-1]的匹配结果
                if (sc == pc || pc == '.') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (pc == '*') {
                    /**
                     * p当前是*
                     * case1: *前的字母取0个，dp[i][j-2] == true ->dp[i][j] = true
                     * case2: *前的字母取多个, 判断*前一个字母和s当前字母能否匹配，如果可以则可以将i-1，j的匹配结果传递过来 e.g s = aaa, p = a*
                     */
                    if (dp[i][j-2]) {
                        dp[i][j] = true;
                    } else if (sc == p.charAt(j-2) || p.charAt(j-2) == '.') {
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }
        }
        
        return dp[m][n];
    }
}