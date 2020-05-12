class LC1416 {
    public int numberOfArrays(String s, int k) {
        int n = s.length();
        long[] dp = new long[n + 1];
        int mod = 1000000007;
        
        int len = Math.min(String.valueOf(k).length(), String.valueOf(Integer.MAX_VALUE).length());
        
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 0 && i - j <= len; j--) {
                if (s.charAt(j) == '0') continue;
                long num = Long.parseLong(s.substring(j, i));
                if (num >= 1 && num <= k) {
                    dp[i] = (dp[i] + dp[j]) % mod;
                }
            }
        }
        
        return (int) dp[n];
    }

    /**
     * Time : n * logk
     */
    public int numberOfArrays_bottom_up(String s, int k) {
        int n = s.length();
        long[] dp = new long[n + 1];
        int mod = 1000000007;
        
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') continue;
            long num = 0;
            //在[i, j]这个区间进行切分 因为j > i所以dp[j]已经计算过，再看substring(j~n)是否<=k
            for (int j = i + 1; j <= n; j++) {
                //这里因为每次乘以10然后与k比较，所以复杂度是log(10)K
                num = num * 10 + s.charAt(j - 1) - '0';
                if (num > k) break;
                
                dp[i] = (dp[i] + dp[j]) % mod;
            }
        }
        
        return (int) dp[0];
    }
}