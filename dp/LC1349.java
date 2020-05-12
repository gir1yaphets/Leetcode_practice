import java.util.*;
class LC1349 {
    /**
     * T: O(m * 2^n)
     */
    public int maxStudents(char[][] seats) {
        int m = seats.length, n = seats[0].length;
        int state = 1 << n;
        int[][] dp = new int[m][state];
        int[] rowMask = new int[m];
        
        for (int[] row : dp) Arrays.fill(row, -1);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //将每一行的状态记录成一个2进制数存在rowMask中，比如.#. 那么就是101 = 5
                rowMask[i] = ((rowMask[i] << 1) | (seats[i][j] == '.' ? 1 : 0));
            }
        }
        
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < state; j++) {
                //rowMask[i] & j != j判断当前的j是否是该行的状态
                //j & (j >> 1) != 0 检查当前这个j左右是否有相邻的1,即..相邻，不合法
                if ((rowMask[i] & j) != j || (j & (j >> 1)) != 0) continue;
                
                if (i == 0) {
                    dp[i][j] = Integer.bitCount(j);
                } else {
                    //枚举所有k，看k是否是能对当前行j有效的状态，即左前方和右前方不能是1
                    //如果k对于当前是合法的，dp[j]就可以从dp[i-1][k]转移过来
                    //dp[i-1][k] != -1是一个剪枝，如果k本身是不合法的，比如k是有连续的1出现，那么dp[i-1][k]== -1,这时候不进行计算
                    for (int k = 0; k < state; k++) {
                        if ((j & (k >> 1)) == 0 && (k & (j >> 1)) == 0 && dp[i-1][k] != -1) {
                            dp[i][j] = Math.max(dp[i][j], Integer.bitCount(j) + dp[i-1][k]);
                        }
                    }
                }
                
                //当前行i的最大值不一定出现在什么状态，所以要对所有的j取一个最大值
                res = Math.max(res, dp[i][j]);
            }
        }
        
        return res;
    }
    
}