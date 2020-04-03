
/**
 * 1.相同值的可以都取, e.g[2,2,3,3,3,4] 取一次3以后2和4都delete掉了 所以3可以都取
 * 2.转换成198 house robber问题， 将上面数组转换成对应元素的和index是数字arr[index]是数字对应的和 [0,0,4,9,4]
 * 3.这样相邻的不取dp[i] = Math.max(dp[i-1], dp[i-2] + arr[i])
 */
public class LC740 {
    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int max = 0;
        for (int i = 0; i < nums.length; i++) max = Math.max(max, nums[i]);
        
        int[] arr = new int[max + 1];
        
        for (int num : nums) {
            arr[num] += num;
        }
        
        int dp1 = 0, dp2 = 0;
        
        for (int i = 0; i < max + 1; i++) {
            int next = Math.max(dp2, dp1 + arr[i]);
            dp1 = dp2;
            dp2 = next;
        }
        
        return dp2;
    }
}