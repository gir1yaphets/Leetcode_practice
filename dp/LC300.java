import java.util.*;

class LC300 {
    /**
     * dp[i] 以i为长度的数组中最长的上升子序列
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        
        int[] dp = new int[n];
        int max = 1;
        
        for (int i = 0; i < n; i++) {
            //起点也可能出现在任何位置，所以每个dp[i]初始化为1
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    //因为j是从0~i, 所以会反复更新dp[i],选择长度最大的
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            
            //之所以要统计max是因为最长序列不一定出现在结尾，有可能出现在dp[i]的任何位置
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }

    /**
     * https://leetcode.com/problems/longest-increasing-subsequence/discuss/74824/JavaPython-Binary-search-O(nlogn)-time-with-explanation
     * 维护一个tail数组，记录当前长度的结尾数字 这个tail数组一定单调递增
     * 遍历整个nums数组，当遇到一个数字比tail中最后一个数字大的时候，记录该数字，并更新tail数组size
     * 如果遇到一个数字比当前tail数组中某一个数字小，则更新这个数字
     * [10,9,2,5,3,7,101,18]
     * len: 1 [10] tail = [10]
     * len:   [10]不要,[9] tail = [9]
     * len:   [10],[9]不要,[2] tail = [2]
     * len: 2 [10],[9],[2],[5] tail = [2],[5]
     *        [10],[9],[2],[5]不要 [3] tail = [2],[3]
     * len: 3 [10],[9],[2],[5]不要 [3],[7] tail = [2],[3],[7]
     * @param nums
     * @return
     */
    public int lengthOfLIS_Dp_BinarySearch(int[] nums) {
        int n = nums.length;
        
        int[] tail = new int[n];
        int size = 0;
        
        for (int num : nums) {
            int lo = 0, hi = size;
            //二分法找到tail中大于当前值num的最小值
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (tail[mid] < num) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            
            tail[lo] = num;
            
            if (lo == size) {
                size++;
            }
        }
        
        return size;
    }
}