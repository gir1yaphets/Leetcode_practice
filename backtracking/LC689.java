import java.util.*;
class LC689 {
    public static void main(String[] args) {
        // int[] nums = {7,13,20,19,19,2,10,1,1,19};
        int[] nums = {1,2,1,2,6,7,5,1};
        new LC689().maxSumOfThreeSubarrays(nums, 2);
    }

    /**
     * 方法：先固定中间subarray起点的范围，然后根据预先求出的左边右边最大subarray的和求出总的最大的subarray
     */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length;
        int n = len - k + 1;
        int[] dp = new int[n]; //total count of subarray
        int[] left = new int[n], right = new int[n]; //left[i]表示左边到i和最大的subarray的起点index
        
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            
            if (i >= k) {
                sum -= nums[i - k];
            }
            
            if (i >= k - 1) {
                dp[i - k + 1] = sum;
            }
        }
        
        int lmax = 0;
        for (int i = 0; i < n; i++) {
            if (dp[lmax] < dp[i]) {
                lmax = i;
            }
            
            left[i] = lmax;
        }
        
        int rmax = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            //注意因为要求最小字典序，所以这里要有等号
            if (dp[rmax] <= dp[i]) {
                rmax = i;
            }
            
            right[i] = rmax;
        }
        
        int[] res = new int[3];
        Arrays.fill(res, -1);
        //中间subarray起点的范围[k, n - k)
        for (int i = k; i < n - k; i++) {
            //如果中间起点在i，左边的最大起点在i-k, 而从左边开始和最大的subarray的起点存在left数组中，直接到dp中查找sum
            //e.g 比如k = 2,当前中间subarray起点在index = 3，那么左边最大的起点可以取到index = 1(左边窗口[1,2])，右边可以取index = 5(中间窗口[3,4])
            if (res[0] == -1 || dp[res[0]] + dp[res[1]] + dp[res[2]] < dp[i] + dp[left[i-k]] + dp[right[i+k]]) {
                res[0] = left[i-k];
                res[1] = i;
                res[2] = right[i+k];
            }
        }
                                                
        return res;
    }

    /**
     * ----------------------------------------------------------
     */
    private int[] presum;
    private int[] res;
    private int[] memo;

    /**
     * 无法确定当前这个index到底属于哪一个区间
     * {1,2,1,2,6,7,5,1} 如果最后一个区间是(6,7)最后的区间和是13，如果是(7,5)和是12
     * 但是如果把6放到第二个区间的话 总的和会更大
     */
    public int[] maxSumOfThreeSubarrays_wrong(int[] nums, int k) {
        int n = nums.length;
        presum = new int[n + 1];
        memo = new int[n];
        
        for (int i = 1; i <= n; i++) presum[i] = presum[i-1] + nums[i-1];
        
        res = new int[3];
        dfs(nums, k, 0, 3);
        return res;
    }
    
    private int dfs(int[] nums, int k, int index, int left) {
        if (index + k * left > nums.length) return 0;
        if (left == 0) {
            return 0;
        }
        
        if (memo[index] != 0) return memo[index];

        int sum = 0;
        for (int i = index; i <= nums.length - k * left; i++) {
            int curr = presum[i+k] - presum[i] + dfs(nums, k, i + k, left - 1);
            
            if (curr > sum) {
                sum = curr;
                res[3 - left] = i;
            }
        }
        
        //这个sum只是到当前的最大值，并不能保证总的和最大
        return memo[index] = sum;
    }

    private int max;
    
    public int[] maxSumOfThreeSubarrays_TLE(int[] nums, int k) {
        int n = nums.length;
        presum = new int[n + 1];
        for (int i = 1; i <= n; i++) presum[i] = presum[i-1] + nums[i-1];
        
        res = new int[3];
        dfs(nums, k, 0, 3, 0, new ArrayList<>());
        return res;
    }
    
    private void dfs(int[] nums, int k, int index, int left, int curr, List<Integer> list) {
        if (index + k * left > nums.length) return;
        if (left == 0) {
            if (curr > max) {
                max = curr;
                for (int i = 0; i < 3; i++) {
                    res[i] = list.get(i);
                }
            }
            return;
        }
        
        for (int i = index; i <= nums.length - k * left; i++) {
            list.add(i);
            dfs(nums, k, i + k, left - 1, curr + presum[i+k] - presum[i], list);
        }
        
        list.remove(list.size() - 1);
    }
}