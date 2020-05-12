class LC956 {
    public static void main(String[] args) {
        int[] rods = {1,2,3,4,5,6};
        new LC956().tallestBillboard(rods);
    }

    public int tallestBillboard(int[] rods) {
        //dp[i][j]表示使用前i个rod，高度差为j能达到的最大公共高度
        int[] dp = new int[5001];
        for (int d = 1; d < 5001; d++) dp[d] = -10000;
        for (int x : rods) {
            //注意这里不能直接用curr = dp 否则是同一个数组的引用，互相干扰
            int[] curr = dp.clone();
            for (int d = 0; d + x < 5001; d++) {
                //把当前rod加到长的那一边， 以d+x为高度差能获得的最大公共高度
                dp[d + x] = Math.max(dp[d + x], curr[d]);
                //把当前rod加到短的那一边， 以Math.abs(d-x)为高度差能获得的最大公共高度
                dp[Math.abs(d - x)] = Math.max(dp[Math.abs(d - x)], curr[d] + Math.min(d, x));
            }
        }
        
        return dp[0];
    }
}