class LC730 {
    private int mod = 1000000007;
    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        int[][] memo = new int[n][n];
        
        return (int) dfs(s, 0, n - 1, memo);
    }
    
    private long dfs(String s, int l, int r, int[][] memo) {
        if (l > r) return 0;
        if (l == r) return 1;
        
        if (memo[l][r] != 0) return memo[l][r];

        long count = 0;
        if (s.charAt(l) == s.charAt(r)) {
            int i = l + 1, j = r - 1;
            //向中间找是否存在和两边的字母一样的
            while (i <= j && s.charAt(i) != s.charAt(l)) i++;
            while (i <= j && s.charAt(j) != s.charAt(r)) j--;

            //如果最中间的字母和两边字母一样，需要从2 * dfs + 2中减去1个
            if (i == j) {
                count = 2 * dfs(s, l + 1, r - 1, memo) + 1;
            } else if (i > j) {
                count = 2 * dfs(s, l + 1, r - 1, memo) + 2;
            } else {
                //如果存在和中间的字母和两边字母一样，并且还不是在中间，需要减去中间不一样字母的解的个数 具体见md截图
                count = 2 * dfs(s, l + 1, r - 1, memo) - dfs(s, i + 1, j - 1, memo);
            }
        } else {
            count = dfs(s, l + 1, r, memo) + dfs(s, l, r - 1, memo) - dfs(s, l + 1, r - 1, memo);
        }
        
        
        return memo[l][r] = (int) ((count + mod) % mod);
    }
}