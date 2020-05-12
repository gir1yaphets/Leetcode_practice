import java.util.*;
import java.util.stream.Collectors;

class LC312 {
    public static void main(String[] args) {
        int[] nums = {3,1,5,8};
        new LC312().maxCoins(nums);
    }

    /**
     * brute force
     * TLE
     */
    public int maxCoins(int[] nums) {
        if (nums.length == 0) return 0;
        
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        return dfs(list);
    }
    
    private int dfs(List<Integer> nums) {
        if (nums.size() == 1) {
            return nums.get(0);
        }
        
        int result = 0;
        for (int i = 0; i < nums.size(); i++) {
            int left = i == 0 ? 1 : nums.get(i-1);
            int right = i == nums.size() - 1 ? 1 : nums.get(i + 1);
            int curr = nums.get(i);
            List<Integer> remain = new LinkedList<>();
            for (int j = 0; j < nums.size(); j++) {
                if (i == j) continue;
                remain.add(nums.get(j));
            }
            
            result = Math.max(left * right * curr + dfs(remain), result);
            
        }
        
        return result;
    }

    public int maxCoins_memo(int[] nums) {
        if (nums.length == 0) return 0;

        int[][] dp = new int[nums.length][nums.length];
        return dfs(nums, 0, nums.length - 1, dp);
    }
    
    private int dfs(int[] nums, int start, int end, int[][] dp) {
        if (start > end) {
            return 0;
        }
        
        if (dp[start][end] != 0) return dp[start][end];
        
        int max = 0;
        for (int i = start; i <= end; i++) {
            //这里不能是(i-1) * i * (i + 1) 因为i-1和i+1已经被扎爆了 但是边界还在
            max = Math.max(max, dfs(nums, start, i - 1, dp) + get(nums, i) * get(nums, start - 1) * get(nums, end + 1) + dfs(nums, i + 1, end, dp));
        }
        
        dp[start][end] = max;
        return max;
    }
    
    public int get(int[] nums, int i) {
        if (i == -1 || i == nums.length) {
            return 1;
        }
        
        return nums[i];
    }

    /**
     * 从小的长度开始算
     */
    public int maxCoins_dp(int[] nums) {
        int n = nums.length;
        int[] arr = new int[nums.length + 2];
        arr[0] = 1;
        arr[arr.length -1] = 1;
        
        for (int i = 0; i < nums.length; i++) {
            arr[i+1] = nums[i];
        }
        
        int[][] dp = new int[nums.length + 2][nums.length + 2];

        
        for (int l = 1; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k-1] + arr[k] * arr[i-1] * arr[j + 1] + dp[k+1][j]);
                }
            }
        }
        
        return dp[1][n];
    }
}