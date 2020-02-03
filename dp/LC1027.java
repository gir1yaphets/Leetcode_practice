import java.util.*;

class LC1027 {
    public static void main(String[] args) {
        int[] array = {20,1,15,3,10,5,8};
        longestArithSeqLength(array);
    }

    /**
     * dp[i][d]:以i为长度的数组，以d为公差的等差数列长度
     * dp[i][d] = dp[j][d] + 1 (j为从0~i)
     * @param A
     * @return
     */
    public static int longestArithSeqLength(int[] A) {
        int len = A.length;
        int res = 0;
        Map<Integer, Integer>[] dp = new HashMap[len];
        for (int i = 0; i < len; i++) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                int count = dp[j].getOrDefault(A[i]-A[j], 1);
                count += 1;
                dp[i].put(A[i]-A[j], count);
                
                res = Math.max(count, res);
            }
        }
        
        return res;
    }
}