
public class LC935 {
    public int knightDialer_v1(int N) {
        long[][] dp = new long[N][10];
        
        for (int i = 0; i < 10; i++) {
            dp[0][i] = 1;
        }
        
        int mod = 1000000007;
        for (int i = 1; i < N; i++) {
            dp[i][0] = (dp[i-1][4] + dp[i-1][6]) % mod;
            dp[i][1] = (dp[i-1][6] + dp[i-1][8]) % mod;
            dp[i][2] = (dp[i-1][7] + dp[i-1][9]) % mod;
            dp[i][3] = (dp[i-1][4] + dp[i-1][8]) % mod;
            dp[i][4] = (dp[i-1][3] + dp[i-1][9] + dp[i-1][0]) % mod;
            dp[i][5] = 0;
            dp[i][6] = (dp[i-1][1] + dp[i-1][7] + dp[i-1][0]) % mod;
            dp[i][7] = (dp[i-1][2] + dp[i-1][6]) % mod;
            dp[i][8] = (dp[i-1][1] + dp[i-1][3]) % mod;
            dp[i][9] = (dp[i-1][2] + dp[i-1][4]) % mod;
        }
        
        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum = (sum + dp[N-1][i]) % mod;
        }
        
        return (int) sum;
    }

    public int knightDialer_v2(int N) {
        int[][] map = new int[][]{{4,6},{6,8},{7,9},{4,8},{3,9,0},{},{1,7,0},{2,6},{1,3},{2,4}};
        
        
        int[] dp = new int[10];
        for (int i = 0; i < 10; i++) {
            dp[i] = 1;
        }
        
        int mod = 1000000007;
        for (int i = 1; i < N; i++) {
            int[] next = new int[10];
            for (int j = 0; j < map.length; j++) {
                for (int prev : map[j]) {
                    next[j] = (next[j] + dp[prev]) % mod;
                }
            }
            dp = next;
        }
        
        long sum = 0;
        for (int i = 0; i < 10; i++) {
            sum = (sum + dp[i]) % mod;
        }
        
        return (int) sum;
    }
}