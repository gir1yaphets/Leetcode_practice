import java.util.*;

class LC560 {
    /**
     * T(O(n))
     * @param nums
     * @param k
     * @return
     */
    /**
     * 将(j, i) = k拆为两部分 (0, i) = sum和(0, j-1) = sum - k; 
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int sum = 0, res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        
        //初始化前缀和为0的个数为1, 为了加入sum == k的情况
        map.put(0, 1);
        
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            
            /**
             * 0～i的和是sum, 判断i之前有多少个前缀和从(0 ~ j-1)是sum - k
                那么就存在多少个从j~i的和是k, 这里和位置无关
                e.g {x, x, x, x, x, x} k = 2
                       ⬆️    ⬆️ 
                比如i=5的时候sum=3 (0, 1)和(0, 3)的和为1, 所以前缀和是1的个数就有两个
                那么到i=5的时候和为2的个数为两个 即从(2, 5)和(4, 5)
             */
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            
            //将当前的和继续作为pre_sum存入map记录个数
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return res;
    }

    public int subarraySum_n2(int[] nums, int k) {
        int n = nums.length;
        int sum = 0, res = 0;
        
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = i; j < n ; j++) {
                sum += nums[j];

                if (sum == k) {
                    res += 1;
                }
            }
        }

        return res;
    }

    public int subarraySum_dp_MLE(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n][n];
        int res = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == j) {
                    dp[j][i] = nums[i];
                } else {
                    dp[j][i] = dp[j][i-1] + nums[i];
                }
                
                if (dp[j][i] == k) {
                    res += 1;
                }
            }
        }

        return res;
    }
}